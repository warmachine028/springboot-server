# FROM scratch
# LABEL MAINTAINER="Pritam K."
# ARG PATH=server
# RUN echo "Running L:\n" && ls -l
# FROM maven:3.9.8-eclipse-temurin-22 AS maven
# RUN mvn -v && ls -l
# RUN ls -l && mvn -f server clean install 
# ENTRYPOINT [ "mvn", "-f", ${PATH}, "spring-boot:run" ]

FROM maven:3.9.8-eclipse-temurin-22 AS build
LABEL MAINTAINER="pritam_k@pursuitsoftware.com"
COPY server .
RUN mvn clean install

FROM amazoncorretto:22-alpine3.19-jdk
COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","app.jar" ]

