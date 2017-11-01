FROM anapsix/alpine-java
COPY ./server/target/server-0.0.1-SNAPSHOT.jar /home/server.jar
ENTRYPOINT ["java","-jar","/home/server.jar"]
EXPOSE 8090