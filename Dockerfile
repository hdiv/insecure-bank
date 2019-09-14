# To run the Alpine Tomcat image on a container
# docker run --name boi_web_server -d -p 8080:8080

FROM jeanblanchard/tomcat:8

RUN echo 'Setting up the Apache Tomcat server ...'

ENV MAVEN_VERSION 3.5.4
ENV MAVEN_HOME /usr/lib/mvn
ENV PATH $MAVEN_HOME/bin:$PATH

RUN wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  tar -zxvf apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  rm apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  mv apache-maven-$MAVEN_VERSION /usr/lib/mvn

RUN mkdir -p insecure-bank

ADD . / insecure-bank/

RUN cd insecure-bank && mvn clean package && cp target/insecure-bank.war /opt/tomcat/webapps/

# RUN echo $CATALINA_HOME

RUN echo 'Apache Server set up is complete ...'
