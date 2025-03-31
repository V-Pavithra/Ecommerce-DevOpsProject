# Use Java base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

# Expose port
EXPOSE 8081

# Run jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]
