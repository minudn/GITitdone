FROM maven:latest AS builder
EXPOSE 8080
RUN mkdir app
COPY . ./app
WORKDIR /app
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*
RUN wget -O /app/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-alpine
COPY --from=builder app/target/gamestore-0.0.1-SNAPSHOT.jar gititdone_app.jar
ENV JAVA_OPTS="-javaagent:/app/dd-java-agent.jar"
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-jar", "gititdone_app.jar"]
