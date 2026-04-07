# Stage 1: Build the application using a Maven image with Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and the project source code
COPY pom.xml .
COPY src ./src

# Build the application
# We use -DskipTests to speed up the process and avoid tests failing
# because the database might not be ready yet.
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime container using a smaller JRE image with Java 17
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the generated jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
