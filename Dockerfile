FROM openjdk:8-jdk-alpine
ARG VERSION
RUN mkdir -p /tmp/pcf-td/logs
COPY ./build/libs/pcf-td-${VERSION}.jar pcf-td.jar
COPY ./src/main/resources/application-docker.properties application.properties
VOLUME ["/tmp/pcf-td/logs"]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/pcf-td.jar"]