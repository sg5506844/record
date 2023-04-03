import 'package:record_platform_interface/src/types/types.dart';

/// Recoding configuration
///
/// [encoder]: The audio encoder to be used for recording.
///
/// [bitRate]: The audio encoding bit rate in bits per second.
///
/// [samplingRate]: The sampling rate for audio in samples per second.
///
/// [numChannels]: The numbers of channels for the recording.
/// 1 = mono, 2 = stereo.
///
/// [device]: The device to be used for recording. If null, default device
/// will be selected.
///
class RecordConfig {
  final AudioEncoder encoder;
  final int bitRate;
  final int samplingRate;
  final int numChannels;
  final InputDevice? device;

  const RecordConfig({
    this.encoder = AudioEncoder.aacLc,
    this.bitRate = 128000,
    this.samplingRate = 44100,
    this.numChannels = 2,
    this.device,
  });
}
