cd /home/ec2-user/cresta
aws s3 cp s3://cresta-bucket-app/app.sh .
chmod +x app.sh
. ./app.sh
./gradlew build
nohup ./gradlew bootrun > log.log 2>&1 &

