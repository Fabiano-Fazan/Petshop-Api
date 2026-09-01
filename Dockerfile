FROM gradle:8.14.3-jdk21-alpine AS build
WORKDIR /app
COPY gradle gradle
COPY gradlew gradlew.bat build.gradle settings.gradle ./
COPY src src
RUN chmod +x gradlew && ./gradlew clean bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
