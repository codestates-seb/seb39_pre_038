#!/usr/bin/env bash
cd /home/ubuntu/seb39_pre_038_new
sudo nohup java -jar -Dspring.profiles.active=server pre_project-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null &