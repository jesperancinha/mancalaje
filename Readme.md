# MancalaJE

Almost all developers and engineers do a Mancala implementation at some point in their experimentations.

This is my own implementation.

This implementation allows two users to connect online

The principle is that two or more users login and then one of them decides with whom he wants to play with.

This is just a very simple implementation that I intend to grow further in the future to different possbilities.

## Creation commands

* mvn archetype:generate -DgroupId=steelzack -DartifactId=mancalaje -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

## Installation commands

´´´
$ bower install

$ npm install -g karma-cli

$ npm install karma

$ npm install karma-jasmine

$ npm install karma-coverage

$ npm install phantomjs

$ npm install grunt

$ sudo npm install -g grunt-cli

´´´

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

* AngularJS (To be replaced by AngularJS 2...)

## References:

* https://github.com/eirslett/frontend-maven-plugin

* http://gruntjs.com/getting-started
