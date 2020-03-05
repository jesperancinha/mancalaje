FROM jesperancinha/je-all-build:0.0.1

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apt-get update

RUN ["/bin/bash", "-c", "debconf-set-selections <<< \"postfix postfix/mailname string test\""]

RUN ["/bin/bash", "-c", "debconf-set-selections <<< \"postfix postfix/main_mailer_type string 'No configuration'\""]

RUN apt-get install -y --assume-yes postfix

RUN touch /etc/postfix/main.cf

COPY target/mancalaje-service*.jar ${runningFolder}

COPY entrypoint.sh ${runningFolder}

EXPOSE 8087

EXPOSE 8080

EXPOSE 5432

ENTRYPOINT ["entrypoint.sh"]
