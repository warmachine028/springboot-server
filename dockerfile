FROM scratch
LABEL MAINTAINER="Pritam K."
ARG PATH=server
FROM maven:3.9.8-eclipse-temurin-22 AS maven
ARG PATH=server
RUN mvn -v
RUN mvn -f server clean install 
ENTRYPOINT [ "mvn", "-f", ${PATH}, "spring-boot:run" ]
