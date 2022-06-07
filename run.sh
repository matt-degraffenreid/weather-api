cd /home/ec2-user/cresta
docker build -t bootdocker:6 .
docker run -d --name bootdocker -p 8080:8080 bootdocker:6