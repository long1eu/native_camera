// File created by
// Lung Razvan <long1eu>
// on 21/06/2020

import 'dart:io';

const List<String> _extensions = <String>['.pb.dart', '.pbenum.dart', '.pbjson.dart', '.pbserver.dart'];

void main() {
  final List<String> files = Directory('${Directory.current.path}/lib/src/protos')
      .listSync(recursive: true, followLinks: false)
      .whereType<File>()
      .where((element) => _extensions.any((ext) => element.path.endsWith(ext)))
      .map((e) => e.path.split('/lib/src/').last)
      .map((e) => 'export \'$e\';')
      .toList()
        ..sort();

  File('${Directory.current.path}/lib/src/protos.dart').writeAsStringSync('''library protos;
  
${files.join('\n')}
''');
}
