# MancalaJE

Alternative implementation of Mancala JE

## Creation commands

* mvn archetype:generate -DgroupId=steelzack -DartifactId=mancalaje -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

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

* Spring MVC

* Maven

* REST services

* AngularJS (To be replaced by AngularJS 2...)

* Webpack

* GruntJS

* NPM

## References:

* https://github.com/eirslett/frontend-maven-plugin

* http://gruntjs.com/getting-started
