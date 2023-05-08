#!/bin/sh
while ! nc -z rabbitmq 5672; do sleep 3; done
while ! nc -z mysql 3306; do sleep 3; done
java -jar /opt/app/app.jar