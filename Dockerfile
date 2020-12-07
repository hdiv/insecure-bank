FROM tomcat:8.0-jre8
MAINTAINER Hdiv Security

# Copy the application to tomcat
ADD target/insecure-bank.war /usr/local/tomcat/webapps

# Copy the license file
ADD license.hdiv /usr/local/tomcat/hdiv/

# Copy the agent jar
ADD hdiv-ee-agent.jar /usr/local/tomcat/hdiv/

# Run Tomcat and enjoy!
CMD export JAVA_OPTS="-javaagent:hdiv/hdiv-ee-agent.jar \
  -Dhdiv.config.dir=hdiv/ \
  -Dhdiv.console.url=http://console:8080/hdiv-console-services \
  -Dhdiv.console.token=04db250da579302ca273a958 \
  -Dhdiv.server.name=Testing-Docker \
  -Dhdiv.toolbar.enabled=true" && catalina.sh run