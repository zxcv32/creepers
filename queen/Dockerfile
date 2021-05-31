# Build stage
FROM gradle:7.0.2-jdk16 as build
WORKDIR /home/gradle/app
COPY --chown=gradle:gradle . /home/gradle/app
RUN gradle build --no-daemon

# Run stage
FROM openjdk:16
COPY --from=build /home/gradle/app/build/libs/queen.jar /app/queen.jar
RUN useradd -m user
USER user
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/queen.jar"]
