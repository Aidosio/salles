FROM openjdk:17-jdk AS build

WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/build/libs/salles-1.jar salles-1.jar

ENTRYPOINT ["java", "-jar", "salles-1.jar"]
