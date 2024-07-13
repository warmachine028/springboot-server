FROM amazoncorretto:22-alpine3.19-jdk
ARG JAR_FILE=server/target/*.jar
RUN apt-get-update && apt-get install -y maven
RUN mvn clean install 
ENTRYPOINT [ "mvn", "-f", "server", "spring-boot:run" ]
