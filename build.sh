#!/usr/bin/env bash

machine='dev'

if [[ ! -z "$1" ]]; then
  let machine=$1
fi

docker-machine start ${machine}

docker-machine env ${machine}

eval $(docker-machine env ${machine})

docker-compose down

mvn clean install

cd mancalaje-fe
yarn autoclean --init --force
yarn build
cd ..

docker-compose up -d --build --remove-orphans
