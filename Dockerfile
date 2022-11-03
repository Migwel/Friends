FROM openjdk:17-alpine
COPY target/Friends-0.0.1-SNAPSHOT.jar Friends.jar
ENTRYPOINT ["java","-jar","/Friends.jar"]
