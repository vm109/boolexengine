FROM openjdk:8
RUN mkdir -  /app
WORKDIR /app
ADD ./target/brandbargain-0.0.1-SNAPSHOT.jar /app
CMD ["java","-jar","./brandbargain-0.0.1-SNAPSHOT.jar"]

