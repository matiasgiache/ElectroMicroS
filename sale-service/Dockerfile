FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/sale-service-0.0.1.jar
COPY ${JAR_FILE} app_sale.jar
EXPOSE 9003
ENTRYPOINT ["java","-jar","app_sale.jar"]