FROM openjdk:17-jdk

WORKDIR /app
COPY build/libs/*.jar salles-1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "salles-1.jar"]
