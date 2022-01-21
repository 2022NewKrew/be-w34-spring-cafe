FROM mdock.daumkakao.io/openjdk:16-ea-11-jdk
COPY cafe-0.0.1-SNAPSHOT.jar deploy.jar
ENTRYPOINT ["java", "-Dspring.cloud.vault.app-role.role-id=${ROLE_ID}", "-Dspring.cloud.vault.app-role.secret-id=${SECRET_ID}", "-jar", "deploy.jar"]
