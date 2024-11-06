FROM openjdk:17-jdk-alpine
ARG JAR_FILE=KADDEM/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]