FROM openjdk:17-alpine as builder
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean package


FROM openjdk:17-alpine
COPY --from=builder /app/target/*.jar /app/testtask.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/testtask.jar"]
