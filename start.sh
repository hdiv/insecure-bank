#!/bin/sh

java -jar /app/malicious-server.jar &

java ${JAVA_OPTS} -jar /app/insecure-bank.war
