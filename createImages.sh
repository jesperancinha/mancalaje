#!/usr/bin/env bash
# Use this file to create the images you need ONLY inside minikube ssh
cd /mancalaje/docker-psql
docker build --file=Dockerfile --tag=mancalaje-postgresql:latest --rm=true .
cd /mancalaje/mancalaje-service
docker build --file=Dockerfile --tag=mancalaje:latest --rm=true .
cd /mancalaje/mancalaje-fe/docker-files
docker build --file=Dockerfile --tag=mancalaje-fe:latest --rm=true .
