FROM maven:latest AS builder
RUN mkdir app
COPY . ./app
WORKDIR /app
RUN mvn clean package
FROM openjdk:17-jdk-alpine
COPY --from=builder app/target/gamestore-0.0.1-SNAPSHOT.jar gititdone_app.jar
ENTRYPOINT ["java", "-jar", "gititdone_app.jar"]