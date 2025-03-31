# Use Java base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service.jar

# Expose port
EXPOSE 8084

# Run jar
ENTRYPOINT ["java", "-jar", "payment-service.jar"]