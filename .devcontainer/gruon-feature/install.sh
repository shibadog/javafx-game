#!/bin/bash

curl -O https://download2.gluonhq.com/openjfx/17.0.7/openjfx-17.0.7_linux-x64_bin-sdk.zip

unzip openjfx-17.0.7_linux-x64_bin-sdk.zip

mv ./javafx-sdk-17.0.7 /usr/local/
ln -s /usr/local/javafx-sdk-17.0.7 /usr/local/javafx-sdk

## https://stackoverflow.com/questions/69340703/error-while-loading-shared-libraries-libx11-so-6
## https://stackoverflow.com/questions/64263232/internalerror-running-javafx-15-application-on-windows-subsystem-for-linux

apt update
apt install -y libx11-dev libgtk-3-0