var stonesApp = angular.module("StonesApp", []);

stonesApp.controller('RefreshBoardController', ['$scope', '$http', function($scope, $http) {
   $http.get('http://localhost:8080/stones-game/stones/board').
          success(function(data) {
              $scope.board = data;
          });
  }
]);
