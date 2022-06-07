cd /home/ec2-user/cresta
chmod +x gradlew
./gradlew build && java -jar build/libs/cresta-weather-java.jar
curl "localhost:8080/weather/hello" -X POST -H "Content-Type: application/json" -d '{"user": "123456", "location": "denver"}'
