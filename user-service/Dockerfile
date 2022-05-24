FROM maven:3.8-openjdk-11 AS maven
COPY . /usr/src/app
WORKDIR /usr/src/app
EXPOSE 8083
RUN mvn clean package -Dmaven.test.skip=true
ENTRYPOINT ["mvn", "spring-boot:run"]