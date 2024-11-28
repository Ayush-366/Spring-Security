FROM openjdk:8u131-jdk-alpine
EXPOSE 8090
COPY ./target/BookService.jar BookService.jar 
ENTRYPOINT ["java","-jar","/BookService.jar"]