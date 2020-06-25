// File created by
// Lung Razvan <long1eu>
// on 21/06/2020

part of native_camera2;

class CameraAspectRatio {
  const CameraAspectRatio(this.x, this.y);

  final int x;
  final int y;

  double getValue(bool isPortrait) {
    if (isPortrait) {
      return y / x;
    } else {
      return x / y;
    }
  }

  @override
  String toString() => 'CameraAspectRatio{x: $x, y: $y}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is CameraAspectRatio && runtimeType == other.runtimeType && x == other.x && y == other.y;

  @override
  int get hashCode => x.hashCode ^ y.hashCode;
}
