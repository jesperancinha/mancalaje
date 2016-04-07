var stonesApp = angular.module("StonesApp", []);

stonesApp.controller('RefreshBoardController', ['$scope', '$http', '$interval', '$window',
   function($scope, $http, $interval, $window) {
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
         $http.get('/stones-game/stones/board/leaveBoard').
                 success(function(data) {
                     $scope.board = data;
                 });
         $window.location.href = '/stones-game/stones/board/sessionlist.htm';
   };

   $scope.reload = function () {
           $http.get('/stones-game/stones/board/refreshBoard').
               success(function (data) {
                   $scope.board = data;
                   if(data.gameExit)
                   {
                        $window.location.href = '/stones-game/stones/board/sessionlist.htm';
                   }
               });
   };
 }
]);

