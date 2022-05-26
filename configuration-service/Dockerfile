FROM openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} config-service.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-service.jar"]