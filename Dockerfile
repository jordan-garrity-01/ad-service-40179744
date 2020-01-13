FROM maven:3.6.0 AS MAVEN_STAGE
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:11-jre-slim
COPY --from=MAVEN_STAGE tmp/target/gs-rest-service-0.1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
