var angular = require('angular');

$ = require("jquery")

var app = angular.module("StonesApp", []);

app.controller("StartControl", function($scope, $http, $window) {
       $window.location.href = '/mancalaje/stones/board/login.htm';
});
