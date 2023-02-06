FROM openjdk:11-jre-slim

RUN apt-get update

WORKDIR /app/

COPY ./build/libs/oauth-1.0.0.jar ./server.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","./server.jar"]