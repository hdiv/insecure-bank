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

FROM openjdk:8u342-jre-slim

WORKDIR /app

COPY --from=log4j /home/gradle/src/malicious-server/build/libs/*.jar /app/malicious-server.jar
COPY --from=app /home/gradle/src/build/libs/*.war /app/insecure-bank.war

ADD start.sh /app/start.sh

RUN chmod +x /app/start.sh

# Copy the license file
ADD license.hdiv /app/hdiv/

# Copy the agent jar
ADD hdiv-ee-agent.jar /app/hdiv/

ENV JAVA_OPTS="-javaagent:/app/hdiv/hdiv-ee-agent.jar \
    -Dhdiv.config.dir=/app/hdiv/ \
    -Dhdiv.console.url=http://console:8080/hdiv-console-services \
    -Dhdiv.console.token=04db250da579302ca273a958 \
    -Dhdiv.server.name=Testing-Docker \
    -Dhdiv.toolbar.enabled=true"

# Run Tomcat and enjoy!
CMD /app/start.sh
