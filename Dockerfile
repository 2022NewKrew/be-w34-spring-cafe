FROM openjdk:11

COPY ./build/libs/cafe-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT [ \
    "java", \
    "-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", \
    "-Dspring.datasource.username=${SPRING_DATASOURCE_USER_NAME}", \
    "-Dspring.datasource.password=${SRPING_DATASOURCE_PASSWORD}", \
    "-jar", \
    "/app.jar" \
]
