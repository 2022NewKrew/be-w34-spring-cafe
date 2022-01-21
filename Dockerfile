FROM idock.daumkakao.io/zzng/zzng-kp-gradle-cache AS build
COPY . /app
WORKDIR /app
RUN ./gradlew assemble

FROM idock.daumkakao.io/zzng/adoptopenjdk:11.0.7_10-jdk-hotspot AS run
ENV APP_NAME=jayden-bin-practice
ENV TZ=Asia/Seoul
## spring 패키지 복사
COPY --from=build /app/build/libs/*.jar /app/jayden-bin-practice.jar
EXPOSE 8080
#불필요한 패키지 제거
RUN rm /usr/bin/curl
#deploy 생성 후 권한 설정
RUN adduser --shell /bin/false deploy
RUN chown -R deploy:deploy /app
USER deploy
ENTRYPOINT java \
-Dspring.profiles.active="db" \
-jar /app/jayden-bin-practice.jar