FROM gradle:7.2.4-jdk17

WORKDIR /app

COPY ./ .

RUN gradle installDist

CMD build/install/app/bin/app