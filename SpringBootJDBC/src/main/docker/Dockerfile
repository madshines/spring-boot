FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD springbootjdbc-0.0.1-SNAPSHOT.jar springbootjdbc.jar
RUN sh -c 'touch /springbootjdbc.jar'
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/shanghai" > /etc/timezone
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar -Duser.timezone=GMT+08 /springbootjdbc.jar" ]