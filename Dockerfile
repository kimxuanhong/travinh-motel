# Start with a base image containing Java runtime
FROM openjdk:8

WORKDIR /app

# Make port 8083 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/app-mvt.jar

# Add the application's jar to the container
ADD ${JAR_FILE} /app/app-mvt.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app-mvt.jar" ]
