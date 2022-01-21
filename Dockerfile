FROM eclipse-temurin:11
RUN mkdir /opt/app
COPY ./build/libs/app.jar /opt/app
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/app.jar"]
