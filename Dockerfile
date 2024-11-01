
FROM maven:latest AS builder
WORKDIR /app


COPY . /app
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*
RUN wget -O /app/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-alpine
WORKDIR /app


COPY --from=builder /app/target/gamestore-0.0.1-SNAPSHOT.jar /app/gititdone_app.jar
COPY --from=builder /app/dd-java-agent.jar /app/dd-java-agent.jar

EXPOSE ${PORT}

ENV DD_API_KEY=${DATADOG_API_KEY} \
    DD_SITE="datadoghq.com" \
    DD_DOGSTATSD_PORT=8125 \
    DD_LOGS_ENABLED=true \
    DD_LOGS_CONFIG_LOGS_ENABLED=true \
    JAVA_OPTS="-javaagent:/app/dd-java-agent.jar"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/gititdone_app.jar --server.port=${PORT}"]
