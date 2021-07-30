FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/app
COPY operations /opt/app
COPY target/payment-0.0.1-spring-boot.jar /opt/app
CMD ["java", "-jar", "payment-0.0.1-spring-boot.jar < operations"]