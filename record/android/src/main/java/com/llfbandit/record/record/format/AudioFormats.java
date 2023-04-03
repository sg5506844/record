package com.llfbandit.record.record.format;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Log;

public class AudioFormats {
  private AudioFormats() {
  }

//  https://github.com/dburckh/EncodeRawAudio/blob/develop/app/src/main/java/com/homesoft/encoderawaudio/EncodeViewModel.kt
//  https://android.googlesource.com/platform/frameworks/base/+/master/packages/SystemUI/src/com/android/systemui/screenrecord/ScreenInternalAudioRecorder.java
//  https://github.com/jeryz/AudioRecorder/tree/master/lib_recorder/src/main/java/com/zjr/recorder/processor
//  Samples / amplitude
  // https://www.programcreek.com/java-api-examples/?code=NandagopalR%2FAndroid-Audio-Recorder%2FAndroid-Audio-Recorder-master%2Fapp%2Fsrc%2Fmain%2Fjava%2Fcom%2Fgithub%2Faxet%2Faudiorecorder%2Fencoders%2FFormat3GP.java#
  // https://github.com/HelloHuDi/AudioCapture/blob/master/audiocapture/src/main/java/com/hd/audiocapture/capture/AudioRecordCapture.java

  public static boolean isEncoderSupported(String mimeType) {
    MediaCodecList mcl = new MediaCodecList(MediaCodecList.REGULAR_CODECS);

    for (MediaCodecInfo info : mcl.getCodecInfos()) {
      if (info.isEncoder()) {
        for (String supportedType : info.getSupportedTypes()) {
          if (supportedType.equalsIgnoreCase(mimeType)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  public static String getMimeType(String encoder) {
    switch (encoder) {
      case "aacLc":
      case "aacEld":
      case "aacHe":
        return MediaFormat.MIMETYPE_AUDIO_AAC;
      case "amrNb":
        return MediaFormat.MIMETYPE_AUDIO_AMR_NB;
      case "amrWb":
        return MediaFormat.MIMETYPE_AUDIO_AMR_WB;
      case "wav":
      case "pcm16bit":
      case "pcm8bit":
        return MediaFormat.MIMETYPE_AUDIO_RAW;
      case "opus":
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
          return MediaFormat.MIMETYPE_AUDIO_OPUS;
        }

        Log.d("Record", "Api < 29: Opus is not supported. Reverting to default AAC media format.");
        return MediaFormat.MIMETYPE_AUDIO_AAC;
      case "vorbisOgg":
        return MediaFormat.MIMETYPE_AUDIO_VORBIS;
      default:
        return null;
    }
  }
}
