FROM openjdk:11-jre-slim

COPY ./build/libs/*.jar ./app.jar

ENTRYPOINT [ \
    "java", \
    "-Dspring.datasource.url=${DB_URL}", \
    "-Dspring.datasource.username=${DB_USER}", \
    "-Dspring.datasource.password=${DB_PASSWORD}", \
    "-jar", \
    "app.jar" \
]
