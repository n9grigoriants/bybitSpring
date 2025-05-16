FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
