cd /home/ec2-user/cresta
aws s3 cp s3://cresta-bucket-app/app.sh .
chmod +x app.sh
source ./app.sh
./gradlew build
nohup java -jar build/libs/cresta-weather-java.jar > log.log 2>&1 &

