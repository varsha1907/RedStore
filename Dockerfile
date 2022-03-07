FROM openjdk:11
LABEL MAINTAINER="https://github.com/varsha1907"
LABEL APPLICATION="red store"
WORKDIR /usr/app
ARG JAR_FILE=target/major-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} major.jar
#to run the jar file
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","major.jar"]