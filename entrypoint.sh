#!/usr/bin/env bash
nginx
service postgresql restart
su - postgres -c "createuser root"
su - postgres -c "createdb -O postgres mancalajedb"
su - postgres -c "psql -d mancalajedb -c \"ALTER USER postgres WITH PASSWORD 'admin';\""
service postgresql restart
postfix start &
java -jar -Dspring.profiles.active=prod mancalaje-service-1.1.1-SNAPSHOT.jar