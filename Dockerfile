# Etapa de construcción
FROM maven:latest AS builder
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*
RUN wget -O /app/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-alpine
RUN mkdir /app
COPY --from=builder /app/target/gamestore-0.0.1-SNAPSHOT.jar /app/gititdone_app.jar
COPY --from=builder /app/dd-java-agent.jar /app/dd-java-agent.jar
EXPOSE 8080 10514 8125/udp
ENV DD_API_KEY=${DATADOG_API_KEY} \
    DD_SITE="datadoghq.com" \
    DD_DOGSTATSD_PORT=8125 \
    DD_LOGS_ENABLED=true \
    DD_LOGS_CONFIG_LOGS_ENABLED=true \
    JAVA_OPTS="-javaagent:/app/dd-java-agent.jar"
ENTRYPOINT ["bash", "-c", "/etc/init.d/datadog-agent start && java $JAVA_OPTS -jar /app/gititdone_app.jar"]
#ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar","-Ddd.apm.enabled=true","-jar", "/app/gititdone_app.jar"]