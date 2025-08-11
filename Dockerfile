FROM maven:3.9.8-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM maven:3.9.8-eclipse-temurin-21 AS build

FROM openjdk:21
COPY --from=build /target/Journal_App-0.0.1-SNAPSHOT.jar Journal_App.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Journal_App.jar"]

