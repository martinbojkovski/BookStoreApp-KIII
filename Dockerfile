FROM eclipse-temurin:19-jre-jammy

WORKDIR .

COPY target/*-SNAPSHOT.jar LabB.jar

EXPOSE 8080

CMD ["java", "-jar", "LabB.jar"]
