FROM openjdk:17-jdk

WORKDIR /app
COPY build/libs/*.jar salles-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "salles-0.0.1-SNAPSHOT.jar"]
