# Stones game

## Creation commands

* mvn archetype:generate -DgroupId=jfse -DartifactId=stones-game -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

## Installation commands

* bower install

## How to run:

* mvn clean install

* mvn tomcat7:run

## Endpoints

* Main page: http://localhost:8080/stones-game/

## REST service endpoints:

* To refresh: http://localhost:8080/stones-game/stones/board/refreshBoard

* To restart: http://localhost:8080/stones-game/stones/board/startAgain

* To empty a pit and roll : localhost:8080/stones-game/stones/board/selectPit/{pitId}

## Tecnologies used:

* Bower

* Spring MVC

* Maven

* REST services

* AngularJS