FROM openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "user-service.jar"]
