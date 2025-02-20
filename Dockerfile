# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the build output from the host to the container
COPY build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the active profile to localmysql
ENV SPRING_PROFILES_ACTIVE=localmysql

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]