FROM eclipse-temurin:17
ADD target/spring-boot-app-0.0.1-SNAPSHOT.jar spring-boot-app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","spring-boot-app.jar"]
