<html>
<head>
     <script src="../../resources/js/sessionpicker.bundle.min.js"></script>
</head>
<body ng-app="StonesApp" ng-controller="SessionPicker">
<div>
  <h1>MancalaJE</h1>
  <h2>Login page</h2>
</div>
       <table border="1">
          <tr ng-repeat="session in sessions.sessions" ng-click="startGame(session.id)">
            <td>{{session.userName}}</td>
          </tr>
        </table>

        </br>
        <button ng-click="logOut()">
            Logout
        </button>
</body>
</html>
