FROM alpine:3.14

# Copy the application to tomcat
ADD target/insecure-bank.war /usr/local/tomcat/webapps
