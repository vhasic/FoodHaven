FROM openjdk:12
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rating-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "rating-service.jar"]
