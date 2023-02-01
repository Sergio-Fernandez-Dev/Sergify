### STAGE 1: Build ###
FROM maven:3.8.7-openjdk-18 AS build
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -f pom.xml clean install -DskipTests

### STAGE 2: Run ###
FROM openjdk:18-alpine
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

