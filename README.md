# Getting Started

./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=msvidal/spring-boot-docker

docker run -p 8080:8080 docker.io/msvidal/spring-boot-docker:latest 

curl --location --request POST 'http://localhost:8080/v1/account' \
--header 'Content-Type: application/json' \
--data-raw '{
  "active-card": "true",
  "available-limit": 10
}'

curl --location --request POST 'http://localhost:8080/v1/transaction' \
--header 'Content-Type: application/json' \
--data-raw '{
  "merchant": "Paozinho 3",
  "amount": 5,
  "time": "2019-02-13T10:00:00.000Z"
}'


