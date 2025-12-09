#1 Build the project with Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom and source
COPY pom.xml .
COPY src ./src

# Build and run tests
RUN mvn -B clean package

#2 Runtime image with only the built jar
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/java-2048-1.0.0.jar app.jar

# Default command: run the game jar
CMD ["java", "-jar", "app.jar"]