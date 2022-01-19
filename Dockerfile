FROM openjdk:11-jdk-slim AS build
COPY . .
RUN ./gradlew build

FROM openjdk:11-jre-slim AS run
COPY --from=build ./build/libs/*.jar ./app.jar
ENTRYPOINT [ \
    "java", \
    "-Dspring.datasource.url=${DB_URL}", \
    "-Dspring.datasource.username=${DB_USER}", \
    "-Dspring.datasource.password=${DB_PASSWORD}", \
    "-jar", \
    "app.jar" \
]
