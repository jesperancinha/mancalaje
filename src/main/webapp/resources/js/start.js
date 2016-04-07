var app = angular.module ("StonesApp", ['ngRoute']);

app.controller("StartControl", function($scope, $http, $window) {
       $window.location.href = '/stones-game/stones/board/login.htm';
});