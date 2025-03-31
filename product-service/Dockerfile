# Use Java base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

# Expose port
EXPOSE 8082

# Run jar
ENTRYPOINT ["java", "-jar", "product-service.jar"]
