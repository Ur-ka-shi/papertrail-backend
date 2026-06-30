# Stage 1: Build the application using Maven and Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the source code and pom.xml into the builder container
COPY . .

# Compile and package your code into a executable production .jar file
RUN mvn clean package -DskipTests

# Stage 2: Use a lightweight Java 17 runtime environment to execute the jar
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy only the compiled target .jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Open port 8080 so Render can communicate with your app
EXPOSE 8080

# The command that starts your Spring Boot server
ENTRYPOINT ["java", "-jar", "app.jar"]