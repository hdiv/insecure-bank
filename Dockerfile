FROM tomcat:8.0-alpine
LABEL maintainer="akshayme@synopsys.com"
ADD target/insecure-bank.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
