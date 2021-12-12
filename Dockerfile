# requires "./gradlew build" first...
FROM openjdk:17.0.1-oracle
ENV APP_HOME="/opt/ludus"

WORKDIR "${APP_HOME}"

# copy application file
COPY "./build/libs/*.jar" "ludus.jar"

# create application user and set permissions
RUN adduser \
    --home "${APP_HOME}" \
    --uid 1001 \
    --comment "" \
    --shell /bin/sh \
    ludus
RUN chown -R 1001:1001 \
    "${APP_HOME}"
USER ludus

# run production-ready application :)
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ludus.jar"]
