FROM openjdk:latest
COPY otus-spring-picnet-0.0.1-SNAPSHOT.jar /otus-spring-picnet-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar /otus-spring-picnet-0.0.1-SNAPSHOT.jar
