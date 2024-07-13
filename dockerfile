FROM scratch
LABEL MAINTAINER="Pritam K."
FROM maven:3.9.8-eclipse-temurin-22 AS maven
ARG PATH=server
RUN mvn -f ${PATH} clean install 
ENTRYPOINT [ "mvn", "-f", ${PATH}, "spring-boot:run" ]
