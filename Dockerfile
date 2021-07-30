FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/app
COPY target/payment-0.0.1-SNAPSHOT-spring-boot.jar /opt/app
CMD ["java", "-jar", "/opt/app/payment-0.0.1-SNAPSHOT-spring-boot.jar"]

