#!/bin/bash
nohup java -jar /home/ec2-user/spring-prj/simple-rest-app-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
echo "new application started"
exit 0
