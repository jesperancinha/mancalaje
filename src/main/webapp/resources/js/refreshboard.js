var stonesApp = angular.module("StonesApp", []);

stonesApp.controller('RefreshBoardController', ['$scope', '$http', function($scope, $http) {
   $http.get('/stones-game/stones/board/refreshBoard').
          success(function(data) {
              $scope.board = data;
          });

   $scope.selectPit = function(selectPit){
          $http.get('/stones-game/stones/board/selectPit/' + selectPit).
                  success(function(data) {
                      $scope.board = data;
                  });
   };

   $scope.startAgain = function() {
         $http.get('/stones-game/stones/board/startAgain').
                 success(function(data) {
                     $scope.board = data;
                 });
   };
 }
]);

