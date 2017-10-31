FROM anapsix/alpine-java
MAINTAINER igorKonovalov 
COPY ./server/target/server-0.0.1-SNAPSHOT.jar /home/server.jar
CMD ["java","-jar","/home/server.jar"]