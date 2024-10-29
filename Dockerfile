FROM maven:latest AS builder
EXPOSE 8080
RUN mkdir app
COPY . ./app
WORKDIR /app
RUN wget -O /path/to/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-alpine
COPY --from=builder app/target/gamestore-0.0.1-SNAPSHOT.jar gititdone_app.jar
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-jar", "gititdone_app.jar"]
