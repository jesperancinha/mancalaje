var angular = require('angular');

var stonesApp = angular.module("StonesApp", []);

stonesApp.controller('RefreshBoardController', ['$scope', '$http', '$interval', '$window',
   function($scope, $http, $interval, $window) {
    $interval(function(){
            $scope.reload();
          },1000);

   $http.get('/mancalaje/stones/board/refreshBoard').
          success(function(data) {
              $scope.board = data;
          });

   $scope.selectPit = function(selectPit){
          $http.get('/mancalaje/stones/board/selectPit/' + selectPit).
                  success(function(data) {
                      $scope.board = data;
                  });
   };

   $scope.startBoardGame = function() {
         $http.get('/mancalaje/stones/board/leaveBoard').
                 success(function(data) {
                     $scope.board = data;
                 });
         $window.location.href = '/mancalaje/stones/board/sessionlist.htm';
   };

   $scope.reload = function () {
           $http.get('/mancalaje/stones/board/refreshBoard').
               success(function (data) {
                   $scope.board = data;
                   if(data.gameExit)
                   {
                        $window.location.href = '/mancalaje/stones/board/sessionlist.htm';
                   }
               });
   };
 }
]);

