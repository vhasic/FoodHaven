FROM openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} discovery-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]