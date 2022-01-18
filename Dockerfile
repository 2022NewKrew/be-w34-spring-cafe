FROM openjdk:11-jre-slim
WORKDIR /root
COPY build/libs/cafe-0.0.1-SNAPSHOT.jar .
CMD java -jar cafe-0.0.1-SNAPSHOT.jar
