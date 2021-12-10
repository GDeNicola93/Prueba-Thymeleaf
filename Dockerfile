FROM openjdk:15.0.1
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/prueba_thymeleaf-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]