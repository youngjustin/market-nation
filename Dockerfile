FROM amazoncorretto:20.0.1-alpine3.17

ARG JAR_FILE=build/libs/code-challenge-0.0.1.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

COPY docker-entrypoint.sh docker-entrypoint.sh

RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["./docker-entrypoint.sh"]
