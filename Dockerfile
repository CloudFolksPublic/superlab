# Use official JDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set work directory
WORKDIR /app

# Copy the correct jar file
COPY target/springboot-superlab-1.0.jar app.jar

# Expose the application port
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
