FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.11_9-slim as build

WORKDIR /src

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.11_9-slim

RUN mkdir -p /app
WORKDIR /app

COPY --from=build /src/build/libs/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]

EXPOSE 8080
