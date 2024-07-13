# Update and Install Maven
FROM ubuntu:24.04 AS INSTALL-MAVEN
RUN apt-get -y update && apt-get install -y maven
RUN alias mvn=maven

# Install Java JDK
FROM amazoncorretto:22-alpine3.19-jdk AS INSTALL-JDK
ARG PATH=server

# Install Packages
RUN maven -f ${PATH} clean install 

# Run Server
ENTRYPOINT [ "maven", "-f", ${PATH}, "spring-boot:run" ]
