# STAGE 1: Build del progetto
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia solo pom.xml e scarica le dipendenze
COPY pom.xml .
RUN mvn -ntp -B dependency:go-offline

# Copia tutto il progetto e compila
COPY . .
RUN mvn -ntp -B package -DskipTests

# STAGE 2: Runtime leggero
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia il jar generato dallo stage build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]