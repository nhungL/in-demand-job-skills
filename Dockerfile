## Use an official image as a parent image
#FROM --platform=linux/amd64 openjdk:20-jdk-slim
#
## Set the working directory inside the container
#WORKDIR /gradle1
#
## Copy the application JAR and other necessary files
#COPY build/libs/gradle1-0.0.1-SNAPSHOT.jar .
#
#COPY src/main/resources/just-aura-392121-1cd5b34c872e.json src/main/resources/keyfile.json
#ENV GOOGLE_APPLICATION_CREDENTIALS="src/main/resources/keyfile.json"
#
## Expose the port your application listens on
#EXPOSE 80
#
## Command to run your application
#CMD ["java", "-jar", "gradle1-0.0.1-SNAPSHOT.jar"]
