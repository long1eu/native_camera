package org.reactnative.camera;

import android.media.CamcorderProfile;
import android.os.Build;

import androidx.exifinterface.media.ExifInterface;

import java.util.HashMap;
import java.util.Map;

public class RNCameraViewHelper {

  public static final String[] exifTags = new String[]{
      ExifInterface.TAG_ARTIST,
      ExifInterface.TAG_BITS_PER_SAMPLE,
      ExifInterface.TAG_COMPRESSION,
      ExifInterface.TAG_COPYRIGHT,
      ExifInterface.TAG_DATETIME,
      ExifInterface.TAG_IMAGE_DESCRIPTION,
      ExifInterface.TAG_IMAGE_LENGTH,
      ExifInterface.TAG_IMAGE_WIDTH,
      ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT,
      ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH,
      ExifInterface.TAG_MAKE,
      ExifInterface.TAG_MODEL,
      ExifInterface.TAG_ORIENTATION,
      ExifInterface.TAG_PHOTOMETRIC_INTERPRETATION,
      ExifInterface.TAG_PLANAR_CONFIGURATION,
      ExifInterface.TAG_PRIMARY_CHROMATICITIES,
      ExifInterface.TAG_REFERENCE_BLACK_WHITE,
      ExifInterface.TAG_RESOLUTION_UNIT,
      ExifInterface.TAG_ROWS_PER_STRIP,
      ExifInterface.TAG_SAMPLES_PER_PIXEL,
      ExifInterface.TAG_SOFTWARE,
      ExifInterface.TAG_STRIP_BYTE_COUNTS,
      ExifInterface.TAG_STRIP_OFFSETS,
      ExifInterface.TAG_TRANSFER_FUNCTION,
      ExifInterface.TAG_WHITE_POINT,
      ExifInterface.TAG_X_RESOLUTION,
      ExifInterface.TAG_Y_CB_CR_COEFFICIENTS,
      ExifInterface.TAG_Y_CB_CR_POSITIONING,
      ExifInterface.TAG_Y_CB_CR_SUB_SAMPLING,
      ExifInterface.TAG_Y_RESOLUTION,
      ExifInterface.TAG_APERTURE_VALUE,
      ExifInterface.TAG_BRIGHTNESS_VALUE,
      ExifInterface.TAG_CFA_PATTERN,
      ExifInterface.TAG_COLOR_SPACE,
      ExifInterface.TAG_COMPONENTS_CONFIGURATION,
      ExifInterface.TAG_COMPRESSED_BITS_PER_PIXEL,
      ExifInterface.TAG_CONTRAST,
      ExifInterface.TAG_CUSTOM_RENDERED,
      ExifInterface.TAG_DATETIME_DIGITIZED,
      ExifInterface.TAG_DATETIME_ORIGINAL,
      ExifInterface.TAG_DEVICE_SETTING_DESCRIPTION,
      ExifInterface.TAG_DIGITAL_ZOOM_RATIO,
      ExifInterface.TAG_EXIF_VERSION,
      ExifInterface.TAG_EXPOSURE_BIAS_VALUE,
      ExifInterface.TAG_EXPOSURE_INDEX,
      ExifInterface.TAG_EXPOSURE_MODE,
      ExifInterface.TAG_EXPOSURE_PROGRAM,
      ExifInterface.TAG_EXPOSURE_TIME,
      ExifInterface.TAG_F_NUMBER,
      ExifInterface.TAG_FILE_SOURCE,
      ExifInterface.TAG_FLASH,
      ExifInterface.TAG_FLASH_ENERGY,
      ExifInterface.TAG_FLASHPIX_VERSION,
      ExifInterface.TAG_FOCAL_LENGTH,
      ExifInterface.TAG_FOCAL_LENGTH_IN_35MM_FILM,
      ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT,
      ExifInterface.TAG_FOCAL_PLANE_X_RESOLUTION,
      ExifInterface.TAG_FOCAL_PLANE_Y_RESOLUTION,
      ExifInterface.TAG_GAIN_CONTROL,
      ExifInterface.TAG_ISO_SPEED_RATINGS,
      ExifInterface.TAG_IMAGE_UNIQUE_ID,
      ExifInterface.TAG_LIGHT_SOURCE,
      ExifInterface.TAG_MAKER_NOTE,
      ExifInterface.TAG_MAX_APERTURE_VALUE,
      ExifInterface.TAG_METERING_MODE,
      ExifInterface.TAG_NEW_SUBFILE_TYPE,
      ExifInterface.TAG_OECF,
      ExifInterface.TAG_PIXEL_X_DIMENSION,
      ExifInterface.TAG_PIXEL_Y_DIMENSION,
      ExifInterface.TAG_RELATED_SOUND_FILE,
      ExifInterface.TAG_SATURATION,
      ExifInterface.TAG_SCENE_CAPTURE_TYPE,
      ExifInterface.TAG_SCENE_TYPE,
      ExifInterface.TAG_SENSING_METHOD,
      ExifInterface.TAG_SHARPNESS,
      ExifInterface.TAG_SHUTTER_SPEED_VALUE,
      ExifInterface.TAG_SPATIAL_FREQUENCY_RESPONSE,
      ExifInterface.TAG_SPECTRAL_SENSITIVITY,
      ExifInterface.TAG_SUBFILE_TYPE,
      ExifInterface.TAG_SUBSEC_TIME,
      ExifInterface.TAG_SUBSEC_TIME_DIGITIZED,
      ExifInterface.TAG_SUBSEC_TIME_ORIGINAL,
      ExifInterface.TAG_SUBJECT_AREA,
      ExifInterface.TAG_SUBJECT_DISTANCE,
      ExifInterface.TAG_SUBJECT_DISTANCE_RANGE,
      ExifInterface.TAG_SUBJECT_LOCATION,
      ExifInterface.TAG_USER_COMMENT,
      ExifInterface.TAG_WHITE_BALANCE,
      ExifInterface.TAG_GPS_ALTITUDE_REF,
      ExifInterface.TAG_GPS_AREA_INFORMATION,
      ExifInterface.TAG_GPS_DOP,
      ExifInterface.TAG_GPS_DATESTAMP,
      ExifInterface.TAG_GPS_DEST_BEARING,
      ExifInterface.TAG_GPS_DEST_BEARING_REF,
      ExifInterface.TAG_GPS_DEST_DISTANCE,
      ExifInterface.TAG_GPS_DEST_DISTANCE_REF,
      ExifInterface.TAG_GPS_DEST_LATITUDE,
      ExifInterface.TAG_GPS_DEST_LATITUDE_REF,
      ExifInterface.TAG_GPS_DEST_LONGITUDE,
      ExifInterface.TAG_GPS_DEST_LONGITUDE_REF,
      ExifInterface.TAG_GPS_DIFFERENTIAL,
      ExifInterface.TAG_GPS_IMG_DIRECTION,
      ExifInterface.TAG_GPS_IMG_DIRECTION_REF,
      ExifInterface.TAG_GPS_LATITUDE_REF,
      ExifInterface.TAG_GPS_LONGITUDE_REF,
      ExifInterface.TAG_GPS_MAP_DATUM,
      ExifInterface.TAG_GPS_MEASURE_MODE,
      ExifInterface.TAG_GPS_PROCESSING_METHOD,
      ExifInterface.TAG_GPS_SATELLITES,
      ExifInterface.TAG_GPS_SPEED,
      ExifInterface.TAG_GPS_SPEED_REF,
      ExifInterface.TAG_GPS_STATUS,
      ExifInterface.TAG_GPS_TIMESTAMP,
      ExifInterface.TAG_GPS_TRACK,
      ExifInterface.TAG_GPS_TRACK_REF,
      ExifInterface.TAG_GPS_VERSION_ID,
      ExifInterface.TAG_INTEROPERABILITY_INDEX,
      ExifInterface.TAG_THUMBNAIL_IMAGE_LENGTH,
      ExifInterface.TAG_THUMBNAIL_IMAGE_WIDTH,
      ExifInterface.TAG_DNG_VERSION,
      ExifInterface.TAG_DEFAULT_CROP_SIZE,
      ExifInterface.TAG_ORF_PREVIEW_IMAGE_START,
      ExifInterface.TAG_ORF_PREVIEW_IMAGE_LENGTH,
      ExifInterface.TAG_ORF_ASPECT_FRAME,
      ExifInterface.TAG_RW2_SENSOR_BOTTOM_BORDER,
      ExifInterface.TAG_RW2_SENSOR_LEFT_BORDER,
      ExifInterface.TAG_RW2_SENSOR_RIGHT_BORDER,
      ExifInterface.TAG_RW2_SENSOR_TOP_BORDER,
      ExifInterface.TAG_RW2_ISO,
  };

  // Utilities

  private static int getCamcorderProfileQualityFromCameraModuleConstant(int quality) {
    switch (quality) {
      case 0/*VIDEO_2160P*/:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          return CamcorderProfile.QUALITY_2160P;
        }
      case 1/*VIDEO_1080P*/:
        return CamcorderProfile.QUALITY_1080P;
      case 2/*VIDEO_720P*/:
        return CamcorderProfile.QUALITY_720P;
      case 3/*VIDEO_480P*/:
      case 4/*VIDEO_4x3*/:
        return CamcorderProfile.QUALITY_480P;
    }
    return CamcorderProfile.QUALITY_HIGH;
  }

  public static CamcorderProfile getCamcorderProfile(int quality) {
    CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
    int camcorderQuality = getCamcorderProfileQualityFromCameraModuleConstant(quality);
    if (CamcorderProfile.hasProfile(camcorderQuality)) {
      profile = CamcorderProfile.get(camcorderQuality);
      if (quality == 1/*VIDEO_4x3*/) {
        profile.videoFrameWidth = 640;
      }
    }
    return profile;
  }

  public static Map<String, String> getExifData(ExifInterface exifInterface) {
    Map<String, String> exifMap = new HashMap<>();
    for (String tag : exifTags) {
      String attribute = exifInterface.getAttribute(tag);
      if (attribute != null) {
        exifMap.put(tag, attribute);
      }
    }

    double[] latLong = exifInterface.getLatLong();
    if (latLong != null) {
      exifMap.put(ExifInterface.TAG_GPS_LATITUDE, Double.toString(latLong[0]));
      exifMap.put(ExifInterface.TAG_GPS_LONGITUDE, Double.toString(latLong[1]));
      exifMap.put(ExifInterface.TAG_GPS_ALTITUDE, Double.toString(exifInterface.getAltitude(0)));
    }

    return exifMap;
  }

  @SuppressWarnings("ConstantConditions")
  public static void setExifData(ExifInterface exifInterface, Map<String, String> exifMap) {
    for (String tag : exifTags) {
      if (exifMap.containsKey(tag)) {
        exifInterface.setAttribute(tag, exifMap.get(tag));
      }
    }

    if (exifMap.containsKey(ExifInterface.TAG_GPS_LATITUDE) && exifMap.containsKey(ExifInterface.TAG_GPS_LONGITUDE)) {
      exifInterface.setLatLong(Double.parseDouble(exifMap.get(ExifInterface.TAG_GPS_LATITUDE)), Double.parseDouble(exifMap.get(ExifInterface.TAG_GPS_LONGITUDE)));
    }
    if (exifMap.containsKey(ExifInterface.TAG_GPS_ALTITUDE)) {
      exifInterface.setAltitude(Double.parseDouble(exifMap.get(ExifInterface.TAG_GPS_ALTITUDE)));
    }
  }

  // clears exif values in place
  public static void clearExifData(ExifInterface exifInterface) {
    for (String tag : exifTags) {
      exifInterface.setAttribute(tag, null);
    }

    // these are not part of our tag list, remove by hand
    exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, null);
    exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, null);
    exifInterface.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, null);
  }
}
