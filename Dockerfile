FROM maven:latest AS builder
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y wget bash && rm -rf /var/lib/apt/lists/*
RUN wget -O /app/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'
RUN mvn clean package -DskipTests

# Cambiar de `openjdk:17-jdk-alpine` a `openjdk:17-jdk-slim` para compatibilidad
FROM openjdk:17-jdk-slim

# Instalar dependencias necesarias
RUN apt-get update && apt-get install -y wget gnupg2 curl

# Agregar la clave GPG de Datadog
RUN curl -sSL https://apt.datadoghq.com/DATADOG_APT_KEY.public -o /tmp/datadog-key && \
    gpg --dearmor -o /etc/apt/trusted.gpg.d/datadog.gpg /tmp/datadog-key

# Agregar el repositorio de Datadog
RUN echo "deb https://apt.datadoghq.com/ stable 7 main" > /etc/apt/sources.list.d/datadog.list

# Actualizar la lista de paquetes e instalar el agente de Datadog
RUN apt-get update && apt-get install -y datadog-agent && rm -rf /var/lib/apt/lists/*

# Descargar el agente Java de Datadog
RUN mkdir /app
COPY --from=builder /app/target/gamestore-0.0.1-SNAPSHOT.jar /app/gititdone_app.jar
COPY --from=builder /app/dd-java-agent.jar /app/dd-java-agent.jar

# Exponer puertos necesarios
EXPOSE 8080 10514 8126 8125/udp

# Configurar variables de entorno
ENV DD_API_KEY=${DATADOG_API_KEY} \
    DD_SITE="datadoghq.com" \
    DD_DOGSTATSD_PORT=8125 \
    DD_LOGS_ENABLED=true \
    DD_LOGS_CONFIG_LOGS_ENABLED=true \
    JAVA_OPTS="-javaagent:/app/dd-java-agent.jar"

# Ejecutar el agente de Datadog y la aplicación
ENTRYPOINT ["sh", "-c", "service datadog-agent start && sleep 5 && java $JAVA_OPTS -jar /app/gititdone_app.jar"]