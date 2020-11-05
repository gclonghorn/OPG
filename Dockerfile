FROM openjdk:9
WORKDIR /app/
COPY ./* /app/
RUN javac ./opg/*.java