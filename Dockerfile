FROM openjdk:11

EXPOSE 8080

ARG JAR_FILE_PATH=./build/libs/cafe-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE_PATH} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
