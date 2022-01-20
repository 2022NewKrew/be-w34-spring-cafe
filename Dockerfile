FROM openjdk:11-jre-slim
COPY build/libs/cafe-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
