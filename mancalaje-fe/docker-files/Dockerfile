FROM jesperancinha/je-all-build:0.0.1

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt-get update

COPY default.conf /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/nginx.conf

COPY build /usr/share/nginx/html

ENTRYPOINT ["nginx", "-t"]

EXPOSE 8087

EXPOSE 8080

EXPOSE 5432
