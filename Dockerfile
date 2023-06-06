#FROM openjdk:17-jdk
#
#WORKDIR /app
#COPY build/libs/*.jar salles-1.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "salles-1.jar"]


FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .


#RUN ./gradlew bootJar --no-daemon
FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/salles-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]