FROM openjdk:17-jdk-slim
COPY build/libs/*SNAPSHOT.jar /app/iBank.jar
COPY build/libs/additional.properties /app/

ENV JAVA_OPTS=""
CMD ["java", "-jar", "app/iBank.jar", "--spring.config.location=file:///app/additional.properties"]