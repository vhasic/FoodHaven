FROM openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-gateway.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]