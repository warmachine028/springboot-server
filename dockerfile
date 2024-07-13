FROM scratch
LABEL MAINTAINER="Pritam K."
ARG PATH=server
RUN echo "Running L:\n" && ls -l
FROM maven:3.9.8-eclipse-temurin-22 AS maven
RUN mvn -v && ls -l
RUN ls -l && mvn -f server clean install 
ENTRYPOINT [ "mvn", "-f", ${PATH}, "spring-boot:run" ]
