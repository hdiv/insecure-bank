#!/bin/sh

java -jar /app/malicious-server.jar &

./bin/catalina.sh run
