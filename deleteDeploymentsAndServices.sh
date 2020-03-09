#!/usr/bin/env bash
kubectl delete service mancalaje-postgresql
kubectl delete deployment mancalaje-postgresql

kubectl delete service mancalaje
kubectl delete deployment mancalaje

kubectl delete service mancalaje-fe
kubectl delete deployment mancalaje-fe
