FROM java:12
WORKDIR /app/
COPY ./* ./
RUN javac App.java
