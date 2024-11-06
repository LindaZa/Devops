# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-alpine

# Set build argument for JAR file location (this can be changed during the build process)
ARG JAR_FILE=target/*.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port the application will run on (optional)
EXPOSE 8080
