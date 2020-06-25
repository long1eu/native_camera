package org.reactnative.camera.tasks;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.exifinterface.media.ExifInterface;

import com.google.protobuf.ByteString;

import org.reactnative.camera.RNCameraViewHelper;
import org.reactnative.camera.utils.RNFileUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import eu.long1.nativecamera.proto.CameraState;
import eu.long1.nativecamera.proto.TakePictureRequest;
import eu.long1.nativecamera.proto.TakePictureResponse;
import io.flutter.plugin.common.MethodChannel;

public class ResolveTakenPictureAsyncTask extends AsyncTask<Void, Void, TakePictureResponse> {
  private static final String ERROR_TAG = "E_TAKING_PICTURE_FAILED";
  private MethodChannel.Result mResponse;
  private Bitmap mBitmap;
  private byte[] mImageData;
  private TakePictureRequest mRequest;
  private File mCacheDirectory;
  private CameraState.Orientation mDeviceOrientation;

  public ResolveTakenPictureAsyncTask(byte[] imageData, MethodChannel.Result promise, TakePictureRequest request, File cacheDirectory, CameraState.Orientation deviceOrientation) {
    mResponse = promise;
    mRequest = request;
    mImageData = imageData;
    mCacheDirectory = cacheDirectory;
    mDeviceOrientation = deviceOrientation;
  }


  @SuppressWarnings({"ConstantConditions"})
  @Override
  protected TakePictureResponse doInBackground(Void... voids) {
    TakePictureResponse.Builder response = TakePictureResponse.newBuilder();

    ExifInterface exifInterface = null;
    Map<String, String> exifData = null;
    Map<String, String> exifExtraData = null;
    boolean orientationChanged = false;

    // this should not incurr in any overhead if not read/used
    ByteArrayInputStream inputStream = new ByteArrayInputStream(mImageData);

    response
        .setDeviceOrientation(mDeviceOrientation)
        .setPictureOrientation(mRequest.getOrientation() != CameraState.Orientation.UNRECOGNIZED ? mRequest.getOrientation() : mDeviceOrientation);

    try {
      // this replaces the skipProcessing flag, we will process only if needed, and in
      // an orderly manner, so that skipProcessing is the default behaviour if no options are given
      // and this behaves more like the iOS version.
      // We will load all data lazily only when needed.


      // Rotate the bitmap to the proper orientation if requested
      if (mRequest.getFixOrientation()) {
        exifInterface = new ExifInterface(inputStream);

        // Get orientation of the image from mImageData via inputStream
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

        if (orientation != ExifInterface.ORIENTATION_UNDEFINED) {
          loadBitmap();
          mBitmap = rotateBitmap(mBitmap, getImageRotation(orientation));
          orientationChanged = true;
        }
      }

      if (mRequest.getWidth() != 0) {
        loadBitmap();
        mBitmap = resizeBitmap(mBitmap, mRequest.getWidth());
      }

      if (mRequest.getMirrorImage()) {
        loadBitmap();
        mBitmap = flipHorizontally(mBitmap);
      }


      // EXIF code - we will adjust exif info later if we manipulated the bitmap
      boolean writeExifToResponse = true;
      // default to true if not provided so it is consistent with iOS and with what happens if no
      // processing is done and the image is saved as is.
      boolean writeExifToFile = true;

      if (!mRequest.getExifMap().isEmpty()) {
        exifExtraData = mRequest.getExifMap();
        writeExifToFile = true;
      }

      // Read Exif data if needed
      if (writeExifToResponse || writeExifToFile) {

        // if we manipulated the image, or need to add extra data, or need to add it to the response,
        // then we need to load the actual exif data.
        // Otherwise we can just use w/e exif data we have right now in our byte array
        if (mBitmap != null || exifExtraData != null || writeExifToResponse) {
          if (exifInterface == null) {
            exifInterface = new ExifInterface(inputStream);
          }
          exifData = RNCameraViewHelper.getExifData(exifInterface);

          if (exifExtraData != null) {
            for (Map.Entry<String, String> entry : exifExtraData.entrySet()) {
              exifData.put(entry.getKey(), entry.getValue());
            }
          }
        }

        // if we did anything to the bitmap, adjust exif
        if (mBitmap != null) {
          exifData.put("width", Integer.toString(mBitmap.getWidth()));
          exifData.put("height", Integer.toString(mBitmap.getHeight()));

          if (orientationChanged) {
            exifData.put(ExifInterface.TAG_ORIENTATION, Integer.toString(ExifInterface.ORIENTATION_NORMAL));
          }
        }

        // Write Exif data to the response if requested
        if (writeExifToResponse) {
          for (String key : exifData.keySet()) {
            String value = exifData.get(key);
            response.putExif(key, value);
          }
        }
      }


      // final processing
      // Based on whether or not we loaded the full bitmap into memory, final processing differs
      if (mBitmap == null) {

        // set response dimensions. If we haven't read our bitmap, get it efficiently
        // without loading the actual bitmap into memory
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(mImageData, 0, mImageData.length, options);
        if (options != null) {
          response
              .setWidth(options.outWidth)
              .setHeight(options.outHeight);
        }


        // save to file if requested
        if (!mRequest.getDoNotSave()) {
          // Prepare file output
          File imageFile = new File(getImagePath());

          imageFile.createNewFile();

          FileOutputStream fOut = new FileOutputStream(imageFile);

          // Save byte array (it is already a JPEG)
          fOut.write(mImageData);
          fOut.flush();
          fOut.close();

          // update exif data if needed.
          // Since we didn't modify the image, we only update if we have extra exif info
          if (writeExifToFile && exifExtraData != null) {
            ExifInterface fileExifInterface = new ExifInterface(imageFile.getAbsolutePath());
            RNCameraViewHelper.setExifData(fileExifInterface, exifExtraData);
            fileExifInterface.saveAttributes();
          } else if (!writeExifToFile) {
            // if we were requested to NOT store exif, we actually need to
            // clear the exif tags
            ExifInterface fileExifInterface = new ExifInterface(imageFile.getAbsolutePath());
            RNCameraViewHelper.clearExifData(fileExifInterface);
            fileExifInterface.saveAttributes();
          }
          // else: exif is unmodified, no need to update anything

          // Return file system URI
          response.setUri(Uri.fromFile(imageFile).toString());
        }

        if (mRequest.getReturnBytes()) {
          response.setData(ByteString.copyFrom(mImageData));
        }
      } else {

        // get response dimensions right from the bitmap if we have it
        response
            .setWidth(mBitmap.getWidth())
            .setHeight(mBitmap.getHeight());

        // Cache compressed image in imageStream
        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, getQuality(), imageStream);


        // Write compressed image to file in cache directory unless otherwise specified
        if (!mRequest.getDoNotSave()) {
          String filePath = writeStreamToFile(imageStream);

          // since we lost any exif data on bitmap creation, we only need
          // to add it if requested
          if (writeExifToFile && exifData != null) {
            ExifInterface fileExifInterface = new ExifInterface(filePath);
            RNCameraViewHelper.setExifData(fileExifInterface, exifData);
            fileExifInterface.saveAttributes();
          }
          File imageFile = new File(filePath);
          response.setUri(Uri.fromFile(imageFile).toString());
        }

        // Write base64-encoded image to the response if requested
        if (mRequest.getReturnBytes()) {
          response.setData(ByteString.copyFrom(imageStream.toByteArray()));
        }
      }

      return response.build();
    } catch (Resources.NotFoundException e) {
      new Handler(Looper.getMainLooper()).post(() -> mResponse.error(ERROR_TAG, "Documents directory of the app could not be found.", e.toString()));
      e.printStackTrace();
    } catch (IOException e) {
      new Handler(Looper.getMainLooper()).post(() -> mResponse.error(ERROR_TAG, "An unknown I/O exception has occurred.", e.toString()));
      e.printStackTrace();
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return null;
  }


  @Override
  protected void onPostExecute(TakePictureResponse response) {
    super.onPostExecute(response);
    // If the response is not null everything went well and we can resolve the promise.
    if (response != null) {
      mResponse.success(response.toByteArray());
    }
  }


  private int getQuality() {
    return (int) (mRequest.getQuality() * 100);
  }

  // loads bitmap only if necessary
  private void loadBitmap() throws IOException {
    if (mBitmap == null) {
      mBitmap = BitmapFactory.decodeByteArray(mImageData, 0, mImageData.length);
    }
    if (mBitmap == null) {
      throw new IOException("Failed to decode Image Bitmap");
    }
  }

  private String writeStreamToFile(ByteArrayOutputStream inputStream) throws IOException {
    String outputPath = null;
    IOException exception = null;
    FileOutputStream outputStream = null;

    try {
      outputPath = getImagePath();
      outputStream = new FileOutputStream(outputPath);
      inputStream.writeTo(outputStream);
    } catch (IOException e) {
      e.printStackTrace();
      exception = e;
    } finally {
      try {
        if (outputStream != null) {
          outputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (exception != null) {
      throw exception;
    }

    return outputPath;
  }

  private Bitmap rotateBitmap(Bitmap source, int angle) {
    Matrix matrix = new Matrix();
    matrix.postRotate(angle);
    return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
  }

  private Bitmap resizeBitmap(Bitmap bm, int newWidth) {
    int width = bm.getWidth();
    int height = bm.getHeight();
    float scaleRatio = (float) newWidth / (float) width;

    return Bitmap.createScaledBitmap(bm, newWidth, (int) (height * scaleRatio), true);
  }

  private Bitmap flipHorizontally(Bitmap source) {
    Matrix matrix = new Matrix();
    matrix.preScale(-1.0f, 1.0f);
    return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
  }

  // Get rotation degrees from Exif orientation enum

  private int getImageRotation(int orientation) {
    int rotationDegrees = 0;
    switch (orientation) {
      case ExifInterface.ORIENTATION_ROTATE_90:
        rotationDegrees = 90;
        break;
      case ExifInterface.ORIENTATION_ROTATE_180:
        rotationDegrees = 180;
        break;
      case ExifInterface.ORIENTATION_ROTATE_270:
        rotationDegrees = 270;
        break;
    }
    return rotationDegrees;
  }

  private String getImagePath() throws IOException {
    if (mRequest.getPath().isEmpty()) {
      return RNFileUtils.getOutputFilePath(mCacheDirectory, ".jpg");
    }

    return mRequest.getPath();
  }
}
