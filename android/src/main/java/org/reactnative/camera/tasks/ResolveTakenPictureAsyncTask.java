package org.reactnative.camera.tasks;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;

import androidx.exifinterface.media.ExifInterface;

import org.reactnative.camera.RNCameraViewHelper;
import org.reactnative.camera.utils.RNFileUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodChannel;

public class ResolveTakenPictureAsyncTask extends AsyncTask<Void, Void, Map<String, Object>> {
    private static final String ERROR_TAG = "E_TAKING_PICTURE_FAILED";
    private MethodChannel.Result mPromise;
    private Bitmap mBitmap;
    private byte[] mImageData;
    private Map<String, Object> mOptions;
    private File mCacheDirectory;
    private int mDeviceOrientation;
    private PictureSavedDelegate mPictureSavedDelegate;

    public ResolveTakenPictureAsyncTask(byte[] imageData, MethodChannel.Result promise, Map<String, Object> options, File cacheDirectory, int deviceOrientation, PictureSavedDelegate delegate) {
        mPromise = promise;
        mOptions = options;
        mImageData = imageData;
        mCacheDirectory = cacheDirectory;
        mDeviceOrientation = deviceOrientation;
        mPictureSavedDelegate = delegate;
    }

    @SuppressWarnings("ConstantConditions")
    private int getQuality() {
        return (int) ((Double) mOptions.get("quality") * 100);
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

    @SuppressWarnings({"ConstantConditions", "unchecked"})
    @Override
    protected Map<String, Object> doInBackground(Void... voids) {
        Map<String, Object> response = new HashMap<>();
        ByteArrayInputStream inputStream = null;
        ExifInterface exifInterface = null;
        Map<String, Object> exifData = null;
        Map<String, Object> exifExtraData = null;

        boolean orientationChanged = false;

        response.put("deviceOrientation", mDeviceOrientation);
        response.put("pictureOrientation", mOptions.containsKey("orientation") ? (Integer) mOptions.get("orientation") : mDeviceOrientation);


        try {
            // this replaces the skipProcessing flag, we will process only if needed, and in
            // an orderly manner, so that skipProcessing is the default behaviour if no options are given
            // and this behaves more like the iOS version.
            // We will load all data lazily only when needed.

            // this should not incurr in any overhead if not read/used
            inputStream = new ByteArrayInputStream(mImageData);


            // Rotate the bitmap to the proper orientation if requested
            if (mOptions.containsKey("fixOrientation") && (Boolean) mOptions.get("fixOrientation")) {

                exifInterface = new ExifInterface(inputStream);

                // Get orientation of the image from mImageData via inputStream
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

                if (orientation != ExifInterface.ORIENTATION_UNDEFINED) {
                    loadBitmap();
                    mBitmap = rotateBitmap(mBitmap, getImageRotation(orientation));
                    orientationChanged = true;
                }
            }

            if (mOptions.containsKey("width")) {
                loadBitmap();
                mBitmap = resizeBitmap(mBitmap, (Integer) mOptions.get("width"));
            }

            if (mOptions.containsKey("mirrorImage") && (Boolean) mOptions.get("mirrorImage")) {
                loadBitmap();
                mBitmap = flipHorizontally(mBitmap);
            }


            // EXIF code - we will adjust exif info later if we manipulated the bitmap
            boolean writeExifToResponse = mOptions.containsKey("exif") && (Boolean) mOptions.get("exif");

            // default to true if not provided so it is consistent with iOS and with what happens if no
            // processing is done and the image is saved as is.
            boolean writeExifToFile = true;

            if (mOptions.containsKey("writeExif")) {
                final Object data = mOptions.get("writeExif");
                if (data instanceof Boolean) {
                    writeExifToFile = (Boolean) mOptions.get("writeExif");
                } else if (data instanceof Map) {
                    exifExtraData = (Map<String, Object>) data;
                    writeExifToFile = true;
                }
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
                        for (Map.Entry<String, Object> entry : exifExtraData.entrySet()) {
                            exifData.put(entry.getKey(), entry.getValue());
                        }
                    }
                }

                // if we did anything to the bitmap, adjust exif
                if (mBitmap != null) {
                    exifData.put("width", mBitmap.getWidth());
                    exifData.put("height", mBitmap.getHeight());

                    if (orientationChanged) {
                        exifData.put(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                    }
                }

                // Write Exif data to the response if requested
                if (writeExifToResponse) {
                    response.put("exif", exifData);
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
                    response.put("width", options.outWidth);
                    response.put("height", options.outHeight);
                }


                // save to file if requested
                if (!mOptions.containsKey("doNotSave") || !((Boolean) mOptions.get("doNotSave"))) {

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
                    String fileUri = Uri.fromFile(imageFile).toString();
                    response.put("uri", fileUri);
                }

                if (mOptions.containsKey("base64") && ((Boolean) mOptions.get("base64"))) {
                    response.put("base64", Base64.encodeToString(mImageData, Base64.NO_WRAP));
                }

            } else {

                // get response dimensions right from the bitmap if we have it
                response.put("width", mBitmap.getWidth());
                response.put("height", mBitmap.getHeight());

                // Cache compressed image in imageStream
                ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.JPEG, getQuality(), imageStream);


                // Write compressed image to file in cache directory unless otherwise specified
                if (!mOptions.containsKey("doNotSave") || !((Boolean) mOptions.get("doNotSave"))) {
                    String filePath = writeStreamToFile(imageStream);

                    // since we lost any exif data on bitmap creation, we only need
                    // to add it if requested
                    if (writeExifToFile && exifData != null) {
                        ExifInterface fileExifInterface = new ExifInterface(filePath);
                        RNCameraViewHelper.setExifData(fileExifInterface, exifData);
                        fileExifInterface.saveAttributes();
                    }
                    File imageFile = new File(filePath);
                    String fileUri = Uri.fromFile(imageFile).toString();
                    response.put("uri", fileUri);
                }

                // Write base64-encoded image to the response if requested
                if (mOptions.containsKey("base64") && ((Boolean) mOptions.get("base64"))) {
                    response.put("base64", Base64.encodeToString(imageStream.toByteArray(), Base64.NO_WRAP));
                }

            }

            return response;

        } catch (Resources.NotFoundException e) {
            mPromise.error(ERROR_TAG, "Documents directory of the app could not be found.", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            mPromise.error(ERROR_TAG, "An unknown I/O exception has occurred.", e.toString());
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
        if (mOptions.containsKey("path")) {
            return (String) mOptions.get("path");
        }
        return RNFileUtils.getOutputFilePath(mCacheDirectory, ".jpg");
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

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onPostExecute(Map<String, Object> response) {
        super.onPostExecute(response);

        // If the response is not null everything went well and we can resolve the promise.
        if (response != null) {
            if (mOptions.containsKey("fastMode") && (Boolean) mOptions.get("fastMode")) {
                Map<String, Object> wrapper = new HashMap<>();
                wrapper.put("id", mOptions.get("id"));
                wrapper.put("data", response);
                mPictureSavedDelegate.onPictureSaved(wrapper);
            } else {
                mPromise.success(response);
            }
        }
    }
}
