# Update and Install Maven
FROM ubuntu:24.04 AS INSTALL-MAVEN
RUN apt-get -y update && apt-get install -y maven
ARG PATH=server
RUN alias mvn=maven
RUN mvn -f ${PATH} clean install 
ENTRYPOINT [ "mvn", "-f", ${PATH}, "spring-boot:run" ]

# Install Java JDK
# FROM amazoncorretto:22-alpine3.19-jdk AS INSTALL-JDK

# Install Packages

# Run Server
