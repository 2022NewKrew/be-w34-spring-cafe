FROM eclipse-temurin:11
RUN mkdir /opt/app
# pre-built jar 파일을 scp를 통해 전송하여 사용하기 때문에 이렇게 작성
# git clone 하여 gradle build 이후 사용할 경우 아래를 주석 해제 후 사용
COPY build/libs/cafe-0.0.1-SNAPSHOT.jar /opt/app/cafe-app.jar
# COPY cafe-app.jar /opt/app
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/cafe-app.jar"]
