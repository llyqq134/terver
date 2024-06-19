FROM maven:3.8.8-eclipse-temurin-21-alpine as build 
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/terver-0.0.1-SNAPSHOT.jar terver.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "terver.jar" ]
