#!/bin/bash
echo build...
cd ..
./gradlew clean build

echo sendJar...
scp ./build/libs/cafe-0.0.1-SNAPSHOT.jar deploy@andy-on-boarding.ay1.krane.9rum.cc:/home/deploy/

echo restart Application...
ssh deploy@andy-on-boarding.ay1.krane.9rum.cc "
  killall -9 java;
  cd /home/deploy;
  nohup java -jar cafe-0.0.1-SNAPSHOT.jar --spring.profiles.active=real
"
# 백그라운드
# nohup java -jar cafe-0.0.1-SNAPSHOT.jar --spring.profiles.active=real > /dev/null 2>&1 &
