FROM openjdk:14-alpine
VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE

ADD ${JAR_FILE} jds.jar

ENTRYPOINT java -jar jds.jar