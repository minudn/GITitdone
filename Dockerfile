FROM maven:latest AS builder
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y wget bash && rm -rf /var/lib/apt/lists/*
RUN wget -O /app/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
RUN mvn clean package -DskipTests

# Cambiar de `openjdk:17-jdk-alpine` a `openjdk:17-jdk-slim` para compatibilidad
FROM openjdk:17-jdk-slim

# Instalar el Agente Completo de Datadog
RUN apt-get update && apt-get install -y wget gnupg && \
    sh -c "$(curl -L https://s3.amazonaws.com/dd-agent/scripts/install_script.sh)"

# Descargar el agente Java de Datadog
RUN mkdir /app
COPY --from=builder /app/target/gamestore-0.0.1-SNAPSHOT.jar /app/gititdone_app.jar
COPY --from=builder /app/dd-java-agent.jar /app/dd-java-agent.jar

# Configurar variables de entorno
EXPOSE 8080 10514 8125/udp
ENV DD_API_KEY=${DATADOG_API_KEY} \
    DD_SITE="datadoghq.com" \
    DD_DOGSTATSD_PORT=8125 \
    DD_LOGS_ENABLED=true \
    DD_LOGS_CONFIG_LOGS_ENABLED=true \
    JAVA_OPTS="-javaagent:/app/dd-java-agent.jar"

# Ejecutar el agente de Datadog y la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/gititdone_app.jar"]