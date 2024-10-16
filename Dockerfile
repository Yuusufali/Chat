
FROM openjdk:17

WORKDIR /app


COPY /build/libs/chat-0.0.1-SNAPSHOT.jar backend.jar


EXPOSE 7070

CMD ["java", "-jar", "backend.jar"]
