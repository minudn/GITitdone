FROM maven:latest AS builder
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y wget bash && rm -rf /var/lib/apt/lists/*
RUN wget -O /app/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
RUN mvn clean package -DskipTests
#FROM openjdk:17-jdk-slim
FROM openjdk:17-jdk-alpine
RUN apt-get update && apt-get install -y wget gnupg2 curl
#RUN echo "deb [trusted=yes] https://apt.datadoghq.com/ stable 7 main" > /etc/apt/sources.list.d/datadog.list
#RUN apt-get update && apt-get install -y datadog-agent && rm -rf /var/lib/apt/lists/*
RUN mkdir /app
COPY --from=builder /app/target/gamestore-0.0.1-SNAPSHOT.jar /app/gititdone_app.jar
COPY --from=builder /app/dd-java-agent.jar /app/dd-java-agent.jar
EXPOSE 8080 10514 8126 8125/udp
ENV JAVA_OPTS="-javaagent:/app/dd-java-agent.jar"
#ENV DD_API_KEY=${DATADOG_API_KEY} \
#    DD_SITE="datadoghq.com" \
#    DD_DOGSTATSD_PORT=8125 \
#    DD_LOGS_ENABLED=true \
#    DD_LOGS_CONFIG_LOGS_ENABLED=true \
#    JAVA_OPTS="-javaagent:/app/dd-java-agent.jar -Ddd.agent.host=datadog-agent -Ddd.agent.port=8126"
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-jar", "/app/gititdone_app.jar"]
#ENTRYPOINT ["sh", "-c", "service datadog-agent start && sleep 5 && java $JAVA_OPTS -jar /app/gititdone_app.jar"]