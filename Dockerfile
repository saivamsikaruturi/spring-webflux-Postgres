FROM openjdk:17
EXPOSE 8080
ADD target/postgres-reviews-svc.jar postgres-reviews-svc.jar
ENTRYPOINT ["java","-jar","postgres-reviews-svc.jar"]
