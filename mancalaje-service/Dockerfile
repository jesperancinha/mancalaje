FROM jesperancinha/je-all-build:0.0.1

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt-get update

RUN apt-get install -y --assume-yes postfix

RUN touch /etc/postfix/main.cf

COPY target/mancalaje-service-1.1.1-SNAPSHOT.jar ${runningFolder}

COPY mancalaje-fe/build /usr/share/nginx/html

COPY entrypoint.sh ${runningFolder}

RUN nginx -t

EXPOSE 8087

EXPOSE 8080

EXPOSE 5432

ENTRYPOINT ["entrypoint.sh"]
