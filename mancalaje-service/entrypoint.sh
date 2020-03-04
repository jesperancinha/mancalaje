#!/usr/bin/env bash
nginx
postfix start
java -jar -Dspring.profiles.active=prod mancalaje-service-1.1.1-SNAPSHOT.jar
