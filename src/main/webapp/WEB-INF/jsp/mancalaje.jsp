<html>
<head>
     <script src="../../resources/bower_components/angular/angular.min.js"></script>
     <script src="../../resources/js/refreshboard.js"></script>
</head>
<body ng-app="StonesApp" ng-controller="RefreshBoardController">
<div>
  <h1>MancalaJE</h1>
  <p style="color:green"><b>Current player: {{board.currentPlayerName}}</b></p>
  <p> You are player {{board.sessionPlayer}} </p>
  </br>
  </br>
  <table border="1">
    <tr>
      <td rowspan="2">{{board.largePit1.nStones}}</td>
      <td ng-repeat="test in board.pits1" ng-click="selectPit(test.keyName)">{{test.nStones}}</td>
      <td rowspan="2">{{board.largePit2.nStones}}</td>
    </tr>
    <tr>
      <td ng-repeat="test in board.pits2" ng-click="selectPit(test.keyName)">{{test.nStones}}</td>
    </tr>
  </table>
  </br>
  </br>
  <p style="color:red">{{board.errorMessage}}</p>
</div>
<div ng-show="board.gameOver">
  <p>You have finished the game!</p>
  <p>Winner is {{board.winnerPlayerName}}</p>
</div>
  <button ng-click="startAgain()">
    Start again!
  </button>
</body>
</html>
