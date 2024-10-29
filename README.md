🚀 Proyecto: API de Gestión de Gamestore

Este proyecto es una API RESTful para la gestión de un gamestore, construida con Java y Spring Boot, con integración para la base de datos PostgreSQL y despliegue automatizado en Docker. El pipeline de CI/CD automatiza la ejecución de tests, construcción y despliegue de la aplicación.
Tabla de Contenidos

 📚 Tabla de Contenidos

    🛠️ Tecnologías
    📥 Instalación
    ▶️ Ejecución
        🏠 Localmente
        🐳 Con Docker
    🔗 Endpoints de la API
    🚧 CI/CD
    📊 Monitoreo
    🤝 Contribuciones


🛠️ Tecnologías

    Java 17: Lenguaje principal para el backend.
    Spring Boot: Framework para la creación de la API.
    PostgreSQL: Base de datos relacional utilizada.
    Docker: Para contenerización y despliegue.
    GitHub Actions: Pipeline de CI/CD.
    DataDog: Monitoreo y análisis de rendimiento.

📥 Instalación

    Clona el repositorio:

    bash

git clone https://github.com/minudn/GITitdone.git
cd GITitdone

Configura las variables de entorno para la base de datos en el archivo application.properties o a través de variables de entorno en tu sistema:

properties

spring.datasource.url=jdbc:postgresql://localhost:5432/gamestore
spring.datasource.username=postgres
spring.datasource.password=postgres

Instala dependencias y construye el proyecto:

bash

    mvn clean install

▶️ Ejecución
🏠 Localmente

    Puedes ejecutar la aplicación directamente:

    bash

    mvn spring-boot:run

🐳 Con Docker

    Construcción de la imagen Docker:

    bash

docker build -t tu-usuario/gamestore-app .

Ejecución del contenedor:

bash

    docker run -p 8080:8080 -e DB_URL=jdbc:postgresql://localhost:5432/gamestore -e DB_USER=postgres -e DB_PASSWORD=postgres tu-usuario/gamestore-app
    
🔗 Endpoints de la API

Método	Endpoint	Descripción
GET	/api/products	Obtiene todos los productos
POST	/api/products	Crea un nuevo producto
GET	/api/products/{id}	Obtiene un producto por su ID
PUT	/api/products/{id}	Actualiza un producto existente
DELETE	/api/products/{id}	Elimina un producto por su ID

Nota: Todos los endpoints aceptan y responden en formato JSON.

🚧 CI/CD

La configuración de CI/CD se encuentra en .github/workflows y utiliza GitHub Actions.

    CI (Integración Continua): Cada push a la rama master activa el pipeline de CI:
        Instalación y pruebas unitarias con Maven.
        Construcción de la aplicación.
        Publicación de imagen Docker en Docker Hub.
    CD (Despliegue Continuo): Automatiza el despliegue cada vez que se realiza un merge a la rama master:
        Validación previa: Solo realiza el despliegue si el pipeline de CI se ejecuta sin errores.
        Despliegue en entorno de producción usando la última imagen de Docker.

Para configurar tus secrets de Docker en GitHub:

    DOCKER_USERNAME: Usuario de Docker Hub.
    DOCKER_PASSWORD: Contraseña de Docker Hub.

📊 Monitoreo

DataDog se ha integrado para monitorear el rendimiento de la aplicación en producción.

    Instalación del Agente:
        Si estás usando Docker Compose, agrega el agente de DataDog.
        Configura el agente en el entorno de despliegue con tu API Key de DataDog.

    Integración con Java:
        Inicia la aplicación con el agente de Java de DataDog para capturar métricas detalladas:

        bash

        java -javaagent:/path/to/dd-java-agent.jar \
             -Ddd.service.name=gamestore-app \
             -Ddd.env=production \
             -jar target/gamestore-app.jar

    Dashboards y Alertas: Configura dashboards en DataDog para monitorear el uso de CPU, memoria, tiempos de respuesta, y errores, y define alertas para notificaciones automáticas.

🤝 Contribuciones

    Hace un fork del repositorio.
    Crea una nueva rama.
    Realiza tus cambios y crea un commit.
    Subi tus cambios.
    Abri un Pull Request.
