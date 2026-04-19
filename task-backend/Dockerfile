FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /spring
COPY . .

# Construimos el JAR
RUN mvn clean install -DskipTests

# Variables de entorno
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/spring
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_PROFILES_ACTIVE=docker

# Exponer puerto
EXPOSE 8080

# Ejecutamos la app
CMD ["mvn", "spring-boot:run", "-DskipTests"]