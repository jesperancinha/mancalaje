FROM jesperancinha/je-all-build:0.0.1

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt-get update

COPY docker-files/default.conf /etc/nginx/conf.d/default.conf

COPY docker-files/nginx.conf /etc/nginx/nginx.conf

COPY mancalaje-fe/build /usr/share/nginx/html

COPY entrypoint.sh ${runningFolder}

RUN nginx -t

EXPOSE 8087

EXPOSE 8080

EXPOSE 5432

ENTRYPOINT ["entrypoint.sh"]
