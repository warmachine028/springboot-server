FROM amazoncorretto:22-alpine3.19-jdk
RUN apt-get-update && apt-get install -y maven

ARG PATH=server

RUN mvn -f ${PATH} clean install 

ENTRYPOINT [ "mvn", "-f", ${PATH}, "spring-boot:run" ]
