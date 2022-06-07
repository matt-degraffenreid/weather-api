export GOOGLE_API_KEY={{ssm:GoogleAPIKey}}
export OPEN_WEATHER_API_KEY={{ssm:WeatherAPIKey}}
cd /home/ec2-user/cresta && ./gradlew build
nohup java -jar build/libs/cresta-weather-java.jar > log.log 2>&1 &

