FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
WORKDIR /app
COPY . .

#RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/salles-1.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
