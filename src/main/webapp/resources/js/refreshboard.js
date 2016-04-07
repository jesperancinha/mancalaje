var stonesApp = angular.module("StonesApp", []);

stonesApp.controller('RefreshBoardController', ['$scope', '$http', '$interval', function($scope, $http, $interval) {
    $interval(function(){
            $scope.reload();
          },1000);

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

   $scope.reload = function () {
           $http.get('/stones-game/stones/board/refreshBoard').
               success(function (data) {
                   $scope.board = data;
               });
   };
 }
]);

