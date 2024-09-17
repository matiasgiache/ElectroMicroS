FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/cart-service-0.0.1.jar
COPY ${JAR_FILE} app_cart.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","app_cart.jar"]