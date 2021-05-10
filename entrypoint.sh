#!/usr/bin/env bash
nginx
postfix start
java -jar -Dspring.profiles.active=prod mancalaje-service-2.0.1-SNAPSHOT.jar
