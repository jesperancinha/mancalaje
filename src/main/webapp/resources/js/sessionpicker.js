var app = angular.module ("StonesApp", ['ngRoute']);

app.controller("SessionPicker", function($scope, $http, $window) {
    $http.get('/stones-game/stones/board/sessionlist').
           success(function(data) {
               $scope.sessions = data;
           });

    }
 );



