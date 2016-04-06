var app = angular.module ("StonesApp", ['ngRoute']);

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

        $http.post("/stones-game/stones/board/login", data, config)
        .success(function (data, status, headers, config) {
                        $scope.PostDataResponse = data;
                        $window.location.href = '/stones-game/stones-game.jsp';
                    })
        .error(function (data, status, header, config) {
                        $scope.ResponseDetails = "Data: " + data +
                            "<hr />status: " + status +
                            "<hr />headers: " + header +
                            "<hr />config: " + config;
        });
    };
});