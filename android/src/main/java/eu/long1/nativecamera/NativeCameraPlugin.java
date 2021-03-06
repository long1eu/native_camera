package eu.long1.nativecamera;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.cameraview.AspectRatio;
import com.google.android.cameraview.Size;
import com.google.protobuf.InvalidProtocolBufferException;

import org.reactnative.camera.CameraController;
import org.reactnative.camera.DartMessenger;
import org.reactnative.camera.utils.ScopedContext;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import eu.long1.nativecamera.proto.CameraState;
import eu.long1.nativecamera.proto.InitializeCameraRequest;
import eu.long1.nativecamera.proto.InitializeCameraResponse;
import eu.long1.nativecamera.proto.ListCamerasResponse;
import eu.long1.nativecamera.proto.Point;
import eu.long1.nativecamera.proto.TakePictureRequest;
import eu.long1.nativecamera.util.CameraUtil;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.view.TextureRegistry;

/**
 * NativeCameraPlugin
 */
public class NativeCameraPlugin implements FlutterPlugin, MethodCallHandler {

  private final Map<String, DartMessenger> messengers = new HashMap<>();
  private TextureRegistry textureRegistry;
  private BinaryMessenger binaryMessenger;
  private ScopedContext mScopedContext;
  private MethodChannel channel;
  private CameraController controller;


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
    mScopedContext = new ScopedContext(binding.getApplicationContext());
    textureRegistry = binding.getTextureRegistry();
    binaryMessenger = binding.getBinaryMessenger();
    channel = new MethodChannel(binding.getBinaryMessenger(), "native_camera");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
    switch (call.method) {
      case "ListCameras":
        listCamera(result);
        break;
      case "CreateEventChannel":
        int channelId = (int) call.arguments;
        DartMessenger messenger = new DartMessenger(binaryMessenger, channelId);
        messengers.put(messenger.getName(), messenger);
        result.success(messenger.getName());
        break;
      case "InitializeCamera":
        try {
          final InitializeCameraRequest request = InitializeCameraRequest.parseFrom((byte[]) call.arguments);
          instantiateCamera(request, result);
        } catch (InvalidProtocolBufferException e) {
          e.printStackTrace();
        }
        break;
      case "SetCamera":
        setCamera((String) call.arguments, result);
        break;
      case "SetUsingCamera2Api":
        setUsingCamera2Api((Boolean) call.arguments, result);
        break;
      case "UpdateCamera":
        try {
          final CameraState request = CameraState.parseFrom((byte[]) call.arguments);
          updateCamera(request, result);
        } catch (InvalidProtocolBufferException e) {
          e.printStackTrace();
        }
        break;
      case "AutoFocusPointOfInterest":
        try {
          final Point request = Point.parseFrom((byte[]) call.arguments);
          controller.setAutoFocusPointOfInterest(request.getX(), request.getY());
          result.success(null);
        } catch (InvalidProtocolBufferException e) {
          e.printStackTrace();
        }
        break;
      case "TakePicture":
        try {
          final TakePictureRequest request = TakePictureRequest.parseFrom((byte[]) call.arguments);
          takePicture(request, result);
        } catch (InvalidProtocolBufferException e) {
          e.printStackTrace();
        }
        break;
      /*
      case "start":
        controller.start();
        final Size _size = controller.getPreviewSize();
        Map<String, Object> map = new HashMap<>();
        map.put("previewWidth", _size.getHeight());
        map.put("previewHeight", _size.getHeight());
        result.success(map);
        break;
      case "pausePreview":
        pausePreview();
        result.success(null);
        break;
      case "resumePreview":
        resumePreview();
        result.success(null);
        break;
      case "record":
        record((Map<String, Object>) call.arguments, result);
        break;
      case "stopRecording":
        stopRecording();
        result.success(null);
        break;
      case "getSupportedRatios":
        getSupportedRatios(result);
        break;
      case "getAvailablePictureSizes":
        getAvailablePictureSizes((String) call.arguments, result);
        break;
      case "checkIfRecordAudioPermissionsAreDefined":
        checkIfRecordAudioPermissionsAreDefined(result);
        break;
      case "getSupportedPreviewFpsRange":
        getSupportedPreviewFpsRange(result);
        break;
      case "type":
        @CameraImplementation.Facing int type = (int) call.arguments;
        controller.setFacing(type);
        break;
      case "cameraId":
        String id = (String) call.arguments;
        controller.setCameraId(id);
        break;
      case "flashMode":
        @CameraImplementation.Flash int flash = (int) call.arguments;
        controller.setFlash(flash);
        break;
      case "exposure":
        float exposure = (float) call.arguments;
        controller.setExposureCompensation(exposure);
        break;
      case "autoFocus":
        boolean autoFocus = (boolean) call.arguments;
        controller.setAutoFocus(autoFocus);
        break;
      case "focusDepth":
        float focusDepth = (float) call.arguments;
        controller.setFocusDepth(focusDepth);
        break;
      case "zoom":
        float zoom = (float) call.arguments;
        controller.setZoom(zoom);
        break;
      case "whiteBalance":
        int whiteBalance = (int) call.arguments;
        controller.setWhiteBalance(whiteBalance);
        break;
      case "pictureSize":
        String size = (String) call.arguments;
        controller.setPictureSize(size.equals("None") ? null : Size.parse(size));
        break;
      case "playSoundOnCapture":
        boolean playSoundOnCapture = (boolean) call.arguments;
        controller.setPlaySoundOnCapture(playSoundOnCapture);
        break;
      case "useCamera2Api":
        boolean useCamera2Api = (boolean) call.arguments;
        controller.setUsingCamera2Api(useCamera2Api);
        break;*/
      default:
        result.notImplemented();
        break;
    }
  }

  private void listCamera(@NonNull MethodChannel.Result result) {
    int permission = ContextCompat.checkSelfPermission(mScopedContext.getContext(), Manifest.permission.CAMERA);
    if (permission != PackageManager.PERMISSION_GRANTED) {
      result.error("PERMISSION_ERROR", "You don't have camera permission.", null);
    } else {
      final ListCamerasResponse response = CameraUtil.getCameras(mScopedContext.getContext());
      result.success(response.toByteArray());
    }
  }

  private void instantiateCamera(InitializeCameraRequest request, MethodChannel.Result result) {
    TextureRegistry.SurfaceTextureEntry flutterSurfaceTexture = textureRegistry.createSurfaceTexture();
    SurfaceTexture surfaceTexture = flutterSurfaceTexture.surfaceTexture();
    DartMessenger dartMessenger = messengers.get(request.getChannelName());
    if (dartMessenger == null) {
      result.error("INITIALIZE_ERROR", "You must get a proper event channel name by calling createChannelName first", null);
      return;
    }

    final CameraState cameraState = request.getState();
    controller = new CameraController(mScopedContext.getContext(), dartMessenger, surfaceTexture);
    controller.setUsingCamera2Api(cameraState.getUseCamera2());

    controller.setCameraId(cameraState.getCameraId());
    controller.setZoom(cameraState.getZoom());
    controller.setAspectRatio(AspectRatio.of(cameraState.getRatio().getX(), cameraState.getRatio().getY()));
    controller.setFocusDepth(cameraState.getFocusDepth());
    controller.setAutoFocus(cameraState.getAutoFocus());
    controller.setFlash(cameraState.getFlash());
    controller.setExposureCompensation(cameraState.getExposure());
    controller.setWhiteBalance(cameraState.getWhiteBalance());
    controller.setPlaySoundOnCapture(cameraState.getPlaySoundOnCapture());
    if (controller.start()) {
      final InitializeCameraResponse response = InitializeCameraResponse.newBuilder()
          .setTextureId(flutterSurfaceTexture.id())
          .setState(getCurrentState())
          .build();
      result.success(response.toByteArray());
    } else {
      result.error("CAMERA_START_ERROR", "Unable to start the camera", null);
    }
  }

  private void setCamera(String cameraId, MethodChannel.Result result) {
    controller.setCameraId(cameraId);
    final CameraState state = getCurrentState();
    result.success(state.toByteArray());
  }

  private void setUsingCamera2Api(boolean useCamera2, MethodChannel.Result result) {
    controller.setUsingCamera2Api(useCamera2);
    final CameraState state = getCurrentState();
    result.success(state.toByteArray());
  }

  private void updateCamera(CameraState cameraState, MethodChannel.Result result) {
    controller.setZoom(cameraState.getZoom());
    controller.setAspectRatio(AspectRatio.of(cameraState.getRatio().getX(), cameraState.getRatio().getY()));
    controller.setFocusDepth(cameraState.getFocusDepth());
    controller.setAutoFocus(cameraState.getAutoFocus());
    controller.setFlash(cameraState.getFlash());
    controller.setExposureCompensation(cameraState.getExposure());
    controller.setWhiteBalance(cameraState.getWhiteBalance());
    controller.setPlaySoundOnCapture(cameraState.getPlaySoundOnCapture());
    final CameraState state = getCurrentState();
    result.success(state.toByteArray());
  }

  private CameraState getCurrentState() {
    final Size size = controller.getPreviewSize();
    final AspectRatio aspectRatio = controller.getAspectRatio();
    final CameraState.Builder state = CameraState.newBuilder()
        .setOrientation(controller.getCameraOrientation())
        .setPreviewSize(eu.long1.nativecamera.proto.Size.newBuilder()
            .setWidth(size.getWidth())
            .setHeight(size.getHeight()))
        .setZoom(controller.getZoom())
        .setRatio(eu.long1.nativecamera.proto.CameraAspectRatio.newBuilder()
            .setX(aspectRatio.getX())
            .setY(aspectRatio.getY()))
        .setFocusDepth(controller.getFocusDepth())
        .setCameraId(controller.getCameraId())
        .setAutoFocus(controller.getAutoFocus())
        .setFlash(controller.getFlash())
        .setExposure(controller.getExposureCompensation())
        .setWhiteBalance(controller.getWhiteBalance())
        .setPlaySoundOnCapture(controller.getPlaySoundOnCapture())
        .setUseCamera2(controller.getUsingCamera2Api());

    for (AspectRatio ratio : controller.getSupportedAspectRatios()) {
      eu.long1.nativecamera.proto.CameraAspectRatio proto = eu.long1.nativecamera.proto.CameraAspectRatio.newBuilder()
          .setX(ratio.getX())
          .setY(ratio.getY())
          .build();
      state.addSupportedRatio(proto);
    }

    for (int[] range : controller.getSupportedPreviewFpsRange()) {
      eu.long1.nativecamera.proto.Range proto = eu.long1.nativecamera.proto.Range.newBuilder()
          .setMax(range[0])
          .setMin(range[1])
          .build();
      state.addSupportedPreviewFps(proto);
    }
    return state.build();
  }

  public void pausePreview() {
    if (controller.isCameraOpened()) {
      controller.pausePreview();
    }
  }


  public void resumePreview() {
    if (controller.isCameraOpened()) {
      controller.resumePreview();
    }
  }


  public void takePicture(final TakePictureRequest options, final MethodChannel.Result promise) {
    try {
      final File cacheDirectory = mScopedContext.getCacheDirectory();
      if (controller.isCameraOpened()) {
        controller.takePicture(options, promise, cacheDirectory);
      } else {
        promise.error("E_CAMERA_UNAVAILABLE", "Camera is not running", null);
      }
    } catch (Exception e) {
      promise.error("E_TAKE_PICTURE_FAILED", e.getMessage(), null);
    }
  }


  public void record(final Map<String, Object> options, final MethodChannel.Result promise) {
    final File cacheDirectory = mScopedContext.getCacheDirectory();
    try {
      if (controller.isCameraOpened()) {
        controller.record(options, promise, cacheDirectory);
      } else {
        promise.error("E_CAMERA_UNAVAILABLE", "Camera is not running", controller);
      }
    } catch (Exception e) {
      promise.error("E_CAPTURE_FAILED", e.getMessage(), controller);
    }
  }


  public void stopRecording() {
    if (controller.isCameraOpened()) {
      controller.stopRecording();
    }
  }


  public void getSupportedRatios(final MethodChannel.Result promise) {
    ArrayList<String> result = new ArrayList<>();
    if (controller.isCameraOpened()) {
      Set<AspectRatio> ratios = controller.getSupportedAspectRatios();
      for (AspectRatio ratio : ratios) {
        result.add(ratio.toString());
      }
      promise.success(result);
    } else {
      promise.error("E_CAMERA_UNAVAILABLE", "Camera is not running", null);
    }
  }

  public void getAvailablePictureSizes(final String ratio, final MethodChannel.Result promise) {
    try {
      ArrayList<String> result = new ArrayList<>();
      if (controller.isCameraOpened()) {
        SortedSet<Size> sizes = controller.getAvailablePictureSizes(AspectRatio.parse(ratio));
        for (Size size : sizes) {
          result.add(size.toString());
        }
        promise.success(result);
      } else {
        promise.error("E_CAMERA_UNAVAILABLE", "Camera is not running", null);
      }
    } catch (Exception e) {
      promise.error("E_CAMERA_BAD_VIEWTAG", "getAvailablePictureSizesAsync: Expected a Camera component", null);
    }
  }


  public void checkIfRecordAudioPermissionsAreDefined(final MethodChannel.Result promise) {
    try {
      PackageInfo info = mScopedContext.getPackageManager().getPackageInfo(mScopedContext.getContext().getPackageName(), PackageManager.GET_PERMISSIONS);
      if (info.requestedPermissions != null) {
        for (String p : info.requestedPermissions) {
          if (p.equals(Manifest.permission.RECORD_AUDIO)) {
            promise.success(true);
            return;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    promise.success(false);
  }


  public void getSupportedPreviewFpsRange(final MethodChannel.Result promise) {
    try {
      ArrayList<Map<String, Object>> result = new ArrayList<>();
      ArrayList<int[]> ranges = controller.getSupportedPreviewFpsRange();
      for (int[] range : ranges) {
        HashMap<String, Object> m = new HashMap<>();
        m.put("MAXIMUM_FPS", range[0]);
        m.put("MINIMUM_FPS", range[1]);
        result.add(m);
      }
      promise.success(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
