var angular = require('angular');
var $ = require('jquery');

var app = angular.module("StonesApp", []);

app.controller("LoginUser", function($scope, $http, $window) {
    $scope.loginUser = function (){
        var data = $.param({
                    username: $scope.username,
                });
        var config = {
                        headers : {
                            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                        }
                     }

        $http.post("/mancalaje/stones/board/login", data, config)
        .success(function (data, status, headers, config) {
                        $scope.PostDataResponse = data;
                        $window.location.href = '/mancalaje/stones/board/sessionlist.htm';
                    })
        .error(function (data, status, header, config) {
                        $scope.ResponseDetails = "Data: " + data +
                            "<hr />status: " + status +
                            "<hr />headers: " + header +
                            "<hr />config: " + config;
        });
    };
});
