FROM openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} system-events-service.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "system-events-service.jar"]