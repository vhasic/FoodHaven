FROM openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} recipe-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "recipe-service.jar"]