FROM jesperancinha/je-postgres-all:0.0.2

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN pg_createcluster 12 main --start

COPY docker-files/default.conf /etc/nginx/conf.d/default.conf

COPY docker-files/nginx.conf /etc/nginx/nginx.conf

COPY mancalaje-service/target/mancalaje-service-1.1.1-SNAPSHOT.jar ${runningFolder}

COPY mancalaje-fe/build /usr/share/nginx/html

COPY entrypoint.sh ${runningFolder}

RUN nginx -t

EXPOSE 8087

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]
