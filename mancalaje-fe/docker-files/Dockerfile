FROM jesperancinha/je-all-build:0.0.1

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt-get update

COPY default.conf /etc/nginx/conf.d/default.conf

COPY nginx.conf /etc/nginx/nginx.conf

COPY build /usr/share/nginx/html

CMD nginx -t && nginx && tail -f /dev/null
