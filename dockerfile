# Update and Install Maven
FROM ubuntu:24.04 AS INSTALL-MAVEN
RUN apt-get update && apt-get install -y maven

# Install Java JDK
FROM amazoncorretto:22-alpine3.19-jdk AS INSTALL-JDK
ARG PATH=server

# Install Packages
RUN mvn -f ${PATH} clean install 

# Run Server
ENTRYPOINT [ "mvn", "-f", ${PATH}, "spring-boot:run" ]
