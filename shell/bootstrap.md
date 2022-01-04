<font face="SimSun" size=3>

- windows
~~~bat
java -jar target/OneForAll-0.0.1-SNAPSHOT.jar --server.port=8081
java -jar target/OneForAll-0.0.1-SNAPSHOT.jar --server.port=8082
~~~

- linux
~~~shell

~~~

- mvn
~~~
mvn clean package

~~~

- dockerfile
~~~
FROM openjdk:8
LABEL maintainer="gkl_cc@163.com"
RUN mkdir -p /app
WORKDIR /app
COPY src src
COPY target/*.jar app.jar
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar app.jar
VOLUME ['/app/logs', '/app/files']
EXPOSE 80
~~~
</font>