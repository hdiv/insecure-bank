FROM alpine:3.7.3

# Copy the application to tomcat
ADD target/insecure-bank.war /usr/local/tomcat/webapps
