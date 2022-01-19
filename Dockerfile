FROM openjdk:11-jre-slim

RUN mkdir /leocafe

WORKDIR /leocafe

COPY build/libs/*SNAPSHOT.jar leocafe.jar

ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "leocafe.jar"]

