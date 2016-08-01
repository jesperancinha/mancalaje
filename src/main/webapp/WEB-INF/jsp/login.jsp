<html>
<head>
     <script src="../../resources/js/loginuser.bundle.min.js"></script>
</head>
<body ng-app="StonesApp" ng-controller="LoginUser">
<div>
  <h1>MancalaJE</h1>
  <h2>Login page</h2>
</div>
        <p>Username: <input type="text" name="username" ng-model="username" required /></p>
        <p>No password necessary at this point</p>
        <button ng-click="loginUser()">Submit</button>
</body>
</html>
