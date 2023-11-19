FROM openjdk:17-alpine

RUN mkdir /app

COPY target/*.jar /app/testtask.jar

CMD ["java", "-jar", "/app/testtask.jar"]
