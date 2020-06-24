// File created by
// Lung Razvan <long1eu>
// on 21/06/2020

part of native_camera2;

class AspectRatio {
  const AspectRatio(this.x, this.y);

  final int x;
  final int y;

  double get value => x / y;

  @override
  String toString() => 'AspectRatio{x: $x, y: $y}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is AspectRatio && runtimeType == other.runtimeType && x == other.x && y == other.y;

  @override
  int get hashCode => x.hashCode ^ y.hashCode;
}
