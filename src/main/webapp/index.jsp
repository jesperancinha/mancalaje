<html>
<head>
     <script src="resources/bower_components/angular/angular.min.js"></script>
     <script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
     <script src="resources/bower_components/angular-route/angular-route.min.js"></script>
     <script src="resources/js/loginuser.js"></script>

</head>
<body ng-app="StonesApp" ng-controller="LoginUser">
<div>
  <h1>Stones game</h1>
  <h2>Login page</h2>
</div>
        <p>Username: <input type="text" name="username" ng-model="username" required /></p>
        <p>No password necessary at this point</p>
        <button ng-click="loginUser()">Submit</button>
</body>
</html>
