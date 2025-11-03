FROM openjdk:17-jdk-slim
EXPOSE 9000
COPY target/ecom-fashion-0.0.1-SNAPSHOT.jar ecom-fashion.jar
ENTRYPOINT ["java", "-jar", "ecom-fashion.jar"]
