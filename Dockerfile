FROM java:12
WORKDIR /app/
COPY ./* /app/
RUN javac ./App.java
