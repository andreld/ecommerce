FROM openjdk:8-alpine
VOLUME /tmp
ADD target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar
ENTRYPOINT ["java", "-jar", "/ecommerce.jar"]