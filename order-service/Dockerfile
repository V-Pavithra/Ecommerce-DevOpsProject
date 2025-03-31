# Use Java base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar

# Expose port
EXPOSE 8083

# Run jar
ENTRYPOINT ["java", "-jar", "order-service.jar"]