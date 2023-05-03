FROM gradle:7.4.0-jdk17

RUN apt-get update && apt-get install -yq make

WORKDIR /project
RUN mkdir /project/code

ENV GRADLE_USER_HOME /project/.gradle

COPY . .