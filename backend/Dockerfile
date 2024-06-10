FROM openjdk:17
WORKDIR /tmp
COPY ./target/*.jar .
WORKDIR /tmp
ENTRYPOINT ["java","-jar","Gestion-Expert-0.0.1-SNAPSHOT.jar","-Djava.net.preferIPv4Stack=true"]
expose 8080

