#ubuntu:latest
FROM  eclipse-temurin as builder
WORKDIR application
ADD maven/${project.build.finalName} ./
RUN java -Djarmode=layertools -jar ${project.build.finalName} extract
LABEL authors="mozt"
#ENV JAVA_OPTS "   "
#
#ENTRYPOINT ["top", "-b"]

FROM eclipse-temurin
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.scurity.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]
