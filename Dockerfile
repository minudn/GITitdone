FROM maven:latest AS builder
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y wget bash && rm -rf /var/lib/apt/lists/*
RUN wget -O /app/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y wget gnupg2 curl
RUN mkdir /app
COPY --from=builder /app/target/gamestore-0.0.1-SNAPSHOT.jar /app/gititdone_app.jar
COPY --from=builder /app/dd-java-agent.jar /app/dd-java-agent.jar
EXPOSE 8080 10514 8126 8125/udp
ENV JAVA_OPTS="-javaagent:/app/dd-java-agent.jar"
ENV DD_AGENT_HOST=datadog-agent
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-jar", "/app/gititdone_app.jar"]

