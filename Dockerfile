# To run the Alpine Tomcat image on a container
# docker run --name boi_web_server -d -p 8080:8080

FROM jeanblanchard/tomcat:8

RUN echo 'Setting up the Apache Tomcat server ...'

COPY target/insecure-bank.war /opt/tomcat/webapps/

# RUN echo $CATALINA_HOME

RUN echo 'Apache Server set up is complete ...'
