FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} springpro.jar
ENTRYPOINT ["java","-jar","/springpro.jar"]
EXPOSE 8085:8085