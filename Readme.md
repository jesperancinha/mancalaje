# MancalaJE

## Creation commands

* mvn archetype:generate -DgroupId=jfse -DartifactId=mancalaje -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

## Installation commands

* bower install

## How to run:

* mvn clean install

* mvn tomcat7:run

## Endpoints

* Main page: http://localhost:8080/mancalaje/

## REST service endpoints:

* To refresh: http://localhost:8080/mancalaje/stones/board/refreshBoard

* To restart: http://localhost:8080/mancalaje/stones/board/startBoardGame

* To empty a pit and roll : localhost:8080/mancalaje/stones/board/selectPit/{pitId}

* For a session list : http://localhost:8080/mancalaje/stones/board/sessionlist

## Tecnologies used:

* Bower

* Spring MVC

* Maven

* REST services

* AngularJS
