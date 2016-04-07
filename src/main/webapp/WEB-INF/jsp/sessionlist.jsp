<html>
<head>
     <script src="../../resources/bower_components/angular/angular.min.js"></script>
     <script src="../../resources/bower_components/jquery/dist/jquery.min.js"></script>
     <script src="../../resources/bower_components/angular-route/angular-route.min.js"></script>
     <script src="../../resources/js/sessionpicker.js"></script>

</head>
<body ng-app="StonesApp" ng-controller="SessionPicker">
<div>
  <h1>Stones game</h1>
  <h2>Login page</h2>
</div>
       <table border="1">
          <tr ng-repeat="session in sessions.sessions" ng-click="startGame(session.id)">
            <td>{{session.userName}}</td>
          </tr>
        </table>
</body>
</html>
