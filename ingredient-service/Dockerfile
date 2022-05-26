FROM openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ingredient-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "ingredient-service.jar"]
