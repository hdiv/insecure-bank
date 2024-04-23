FROM gradle:7.3.1-jdk17 AS log4j
LABEL maintainer="Hdiv Security"

COPY --chown=gradle:gradle ./log4j-cve-2021-44228 /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle :malicious-server:bootJar --no-daemon

FROM gradle:8.7-jdk17 AS app
LABEL maintainer="Hdiv Security"

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle :bootWar --no-daemon

FROM tomcat:8.5.100-jre8

RUN mkdir /app
COPY --from=log4j /home/gradle/src/malicious-server/build/libs/*.jar /app/malicious-server.jar

WORKDIR /usr/local/tomcat
ADD start.sh /usr/local/tomcat/

# Copy the application to tomcat
COPY --from=app /home/gradle/src/build/libs/*.war /usr/local/tomcat/webapps/insecure-bank.war

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
  -Dhdiv.toolbar.enabled=true" && ./start.sh
