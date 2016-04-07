var app = angular.module ("StonesApp", ['ngRoute']);

app.controller("SessionPicker", function($scope, $http, $window, $interval) {

    $interval(function(){
               $scope.reload();
             },1000);

    $http.get('/stones-game/stones/board/sessionlist').
           success(function(data) {
               $scope.sessions = data;
           });

    $scope.reload = function (){
        $http.get('/stones-game/stones/board/sessionlist').
               success(function(data) {
                   $scope.sessions = data;
               });

         if($scope.sessions.gamestarted) {
               $window.location.href = '/stones-game/stones/board/stonesgame.htm';
         };
    }



    $scope.startGame = function (userKeepIdIn){
            var data = $.param({
                        id: userKeepIdIn,
                    })
            var config = {
                            headers : {
                                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                            }
                         }

            $http.post("/stones-game/stones/board/startAgain", data, config)
            .success(function (data, status, headers, config) {
                            $scope.PostDataResponse = data;
                            $window.location.href = '/stones-game/stones/board/stonesgame.htm';
                        })
            .error(function (data, status, header, config) {
                            $scope.ResponseDetails = "Data: " + data +
                                "<hr />status: " + status +
                                "<hr />headers: " + header +
                                "<hr />config: " + config;
            });
        };
    }
 );



