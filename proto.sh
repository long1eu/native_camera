#!/usr/bin/env bash

rm -rf lib/src/protos/
mkdir lib/src/protos/

rm -rf android/src/main/java/eu/long1/nativecamera/proto
rm -rf ios/Classes/**.pbobjc.h
rm -rf ios/Classes/**.pbobjc.m

protoc --proto_path=res/ \
       --java_out=lite:android/src/main/java/ \
       --objc_out=ios/Classes/ \
       models.proto \
       native_camera.proto

protoc --dart_out=lib/src/protos/ \
       --proto_path=res/ \
       models.proto \
       google/protobuf/empty.proto \
       google/protobuf/wrappers.proto \
       native_camera.proto

dart res/generate_imports.dart