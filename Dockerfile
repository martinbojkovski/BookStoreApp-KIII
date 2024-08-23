FROM eclipse-temurin:19-jre-jammy

WORKDIR /app

COPY target/LabB-0.0.1-SNAPSHOT.jar bookstore-app.jar

EXPOSE 8080

CMD ["java", "-jar", "bookstore-app.jar"]
