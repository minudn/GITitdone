# Etapa de construcción
FROM maven:latest AS builder
EXPOSE 8080
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*

# Descargar el agente de Datadog en la carpeta /app
RUN wget -O /app/dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'

# Compilar el proyecto
RUN mvn clean package -DskipTests

# Etapa final
FROM openjdk:17-jdk-alpine
# Crear carpeta de aplicación
RUN mkdir /app

# Copiar el JAR de la aplicación y el agente Datadog desde la etapa builder
COPY --from=builder /app/target/gamestore-0.0.1-SNAPSHOT.jar /app/gititdone_app.jar
COPY --from=builder /app/dd-java-agent.jar /app/dd-java-agent.jar

# Configurar JAVA_OPTS
ENV JAVA_OPTS="-javaagent:/app/dd-java-agent.jar"

# Ejecutar la aplicación con el agente
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-jar", "/app/gititdone_app.jar"]