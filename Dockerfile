FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

WORKDIR /app
COPY . .
RUN ./gradlew build

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/build/libs/*.jar salles-1.jar
ENTRYPOINT ["java", "-jar", "salles-1.jar"]
