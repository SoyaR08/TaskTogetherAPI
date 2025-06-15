# Usa una imagen oficial de OpenJDK con Maven para compilar y ejecutar
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

# Copia los archivos del proyecto
COPY pom.xml .
COPY src ./src

# Compila el proyecto y empaqueta sin tests
RUN mvn clean package -DskipTests

# Imagen final con solo Java para ejecutar el .jar
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el .jar compilado desde la etapa build
COPY --from=build /app/target/TaskTogether-0.0.1-SNAPSHOT.jar ./app.jar

# Expone el puerto que usa Spring Boot (por defecto 8080)
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
