FROM java:8
WORKDIR /app/
COPY ./* /app/
RUN javac App.java
