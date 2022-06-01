FROM openjdk:11.0.15-jdk
ARG JAR_FILE=build/libs/\*.jar
ENV OPEN_WEATHER_API_KEY=${OPEN_WEATHER_API_KEY}
ENV GOOGLE_API_KEY=${GOOGLE_API_KEY}
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 80-8080
CMD tail -f /dev/null