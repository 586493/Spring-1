#!/bin/sh
cd /usr/app
java -jar microservices.gateway.jar &
sleep 4 
java -jar microservices.book.jar &
sleep 4
java -jar microservices.author.jar &
sleep 2
java -jar microservices.category.jar 