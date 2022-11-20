FROM openjdk:17-jdk-slim
COPY build/libs/*SNAPSHOT.jar /app/iBank.jar

ENV JAVA_OPTS=""
CMD ["java", "-jar", "app/iBank.jar", "$JAVA_OPTS"]