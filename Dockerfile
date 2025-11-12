FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/microservice-0.0.1-SNAPSHOT app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]