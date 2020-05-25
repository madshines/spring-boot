FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD springbootmybatis-0.0.1-SNAPSHOT.jar springbootmybatis.jar
RUN sh -c 'touch /springbootmybatis.jar'
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/shanghai" > /etc/timezone
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar -Duser.timezone=GMT+08 /springbootmybatis.jar" ]