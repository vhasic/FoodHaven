FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]