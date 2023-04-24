FROM gradle:7.4.2-jdk17

WORKDIR /app

COPY / .

RUN ./gradlew installDist

CMD build/install/app/bin/app