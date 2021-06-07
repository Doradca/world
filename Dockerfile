FROM openjdk:8-jdk-alpine
ADD build/libs/world-0.0.1-SNAPSHOT.jar world.jar
EXPOSE 8080
CMD ["java", "-jar", "world.jar"]
