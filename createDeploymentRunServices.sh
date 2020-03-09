#!/usr/bin/env bash
kubectl create -f docker-psql/postgres-deployment.yaml
kubectl create -f mancalaje-service/mancalaje-deployment.yaml
kubectl create -f mancalaje-fe/mancalaje-fe-deployment.yaml

minikube service mancalaje-fe
