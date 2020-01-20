#!/usr/bin/env bash

mvn clean install -DskipTests

cd mancalaje-fe

npm install -g npm

npm install

npm run build

cd ..

docker-machine start $1

docker-machine env $1

eval $(docker-machine env $1)

docker stop mancalaje

docker rm mancalaje

docker build . --no-cache -t mancalaje-image

docker run --name mancalaje -p 8080:80 -e POSTGRES_PASSWORD=admin -d mancalaje-image