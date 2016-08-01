var angular = require('angular');
var $ = require('jquery');

var app = angular.module ("StonesApp", []);

app.controller("SessionPicker", function($scope, $http, $window, $interval) {

    $interval(function(){
               $scope.reload();
             },1000);

    $http.get('/mancalaje/stones/board/sessionlist').
           success(function(data) {
               $scope.sessions = data;
           });

    $scope.reload = function (){
        $http.get('/mancalaje/stones/board/sessionlist').
               success(function(data) {
                   $scope.sessions = data;
               });

         if($scope.sessions.gamestarted) {
               $window.location.href = '/mancalaje/stones/board/mancalaje.htm';
         };
    }

    $scope.logOut = function (){
        $window.location.href = '/mancalaje/stones/board/login.htm';
    };

    $scope.startGame = function (userKeepIdIn){
            var data = $.param({
                        id: userKeepIdIn,
                    })
            var config = {
                            headers : {
                                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                            }
                         }

            $http.post("/mancalaje/stones/board/startBoardGame", data, config)
            .success(function (data, status, headers, config) {
                            $scope.PostDataResponse = data;
                            $window.location.href = '/mancalaje/stones/board/mancalaje.htm';
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



