FROM tomcat:8.0-alpine
ADD https://get.aquasec.com/microscanner .
RUN chmod +x microscanner
RUN ./microscanner NzBiZGFiYzMzZWRl
LABEL maintainer="akshayme@synopsys.com"
ADD target/insecure-bank.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
