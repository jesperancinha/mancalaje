#!/bin/bash

curl -i -H "Content-Type: application/x-www-form-urlencoded" -c cookies.txt -d "username=player1&password=admin" -X POST http://localhost:8081/login
curl -i -b cookies.txt -X POST http://localhost:8081/api/create
curl -i -H "Content-Type: application/x-www-form-urlencoded" -c cookies2.txt -d "username=player2&password=admin" -X POST http://localhost:8081/login
curl -i -b cookies2.txt -X PUT http://localhost:8081/api/join/16
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/1
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/2
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/3
curl -i -b cookies2.txt -X PUT http://localhost:8081/api/move/16/9
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/5
curl -i -b cookies2.txt -X PUT http://localhost:8081/api/move/16/10
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/2
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/3
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/4
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/6
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/7
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/2
