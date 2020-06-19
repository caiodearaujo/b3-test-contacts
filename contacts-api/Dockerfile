FROM java:openjdk-8-jdk-alpine

# add directly the jar
ADD target/*.jar /app.jar

# to create a modification date
RUN sh -c 'touch /app.jar'

# creates a mount point
VOLUME /tmp
VOLUME /app/files

CMD ["java", "-Xms512m", "-Xmx512m", "-jar", "/app.jar"]

EXPOSE 8080
