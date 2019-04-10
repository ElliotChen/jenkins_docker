FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE
ARG ACTIVE_PROFILE

ADD ${JAR_FILE} jds.jar

RUN echo $ACTIVE_PROFILE

ENV ap=$ACTIVE_PROFILE

ENTRYPOINT java -jar jds.jar --spring.profiles.active=$ap