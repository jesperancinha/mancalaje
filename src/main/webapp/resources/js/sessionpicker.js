var app = angular.module ("StonesApp", ['ngRoute']);

app.controller("SessionPicker", function($scope, $http, $window) {
    $http.get('/stones-game/stones/board/sessionList').
           success(function(data) {
               $scope.board = data;
           });
  }
 ]);

