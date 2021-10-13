FROM adoptopenjdk/openjdk11:ubi
COPY build/libs/*.jar trackerapp.jar
EXPOSE 8083
CMD ["java", "-jar", "trackerapp.jar"]