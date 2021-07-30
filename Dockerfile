FROM maven:3.6.1-jdk-11-slim AS build
COPY src /opt/app/src
COPY pom.xml /opt/app
RUN mvn -f /opt/app/pom.xml clean package -ntp

FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.10_9
COPY --from=build /opt/app/target/payment-0.0.1-spring-boot.jar /app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "/app.jar"]