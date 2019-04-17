FROM adoptopenjdk/openjdk8:latest
ADD target/library-mongo.jar library-mongo.jar
EXPOSE 8080
EXPOSE 27017
ENTRYPOINT ["java", "-jar", "library-mongo.jar"]