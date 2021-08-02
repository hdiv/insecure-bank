FROM maven:3.8.1-openjdk-17

# Copy the application to tomcat
ADD target/insecure-bank.war /usr/local/tomcat/webapps
