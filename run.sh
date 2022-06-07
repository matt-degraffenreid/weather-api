aws s3 cp s3://cresta-bucket-app/app.sh .
chmod +X app.sh
./app.sh
cd /home/ec2-user/cresta && ./gradlew build
nohup java -jar build/libs/cresta-weather-java.jar > log.log 2>&1 &

