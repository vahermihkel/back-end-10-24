# Use the official OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's build artifact to the container
# Assuming your Java application has a .jar file
COPY target/film-rental.jar app.jar

# Expose the port your application runs on (e.g., 8080)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]