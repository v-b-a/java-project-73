FROM gradle:7.3.1-jdk17

WORKDIR /app

COPY ./ .

RUN gradle installDist

CMD ./build/install/app/bin/app