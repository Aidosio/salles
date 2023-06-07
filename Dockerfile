FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN ./gradlew build

WORKDIR /app
COPY . .

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY build/libs/*.jar salles-1.jar
ENTRYPOINT ["java", "-jar", "salles-1.jar"]
