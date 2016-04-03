<html>
<head>
     <script src="resources/bower_components/angular/angular.min.js"></script>
     <script src="resources/js/refreshboard.js"></script>
</head>
<body ng-app="StonesApp" ng-controller="RefreshBoardController">
<div>
  <h1>Stones game</h1>
  <p>Current player: {{board.currentPlayerName}}
  </br>
  </br>
  <table border="1">
    <tr>
      <td rowspan="2">{{board.largePit1.nStones}}</td>
      <td ng-repeat="test in board.pits1">{{test.nStones}}</td>
      <td rowspan="2">{{board.largePit2.nStones}}</td>
    </tr>
    <tr>
      <td ng-repeat="test in board.pits2">{{test.nStones}}</td>
    </tr>
  </table>


</div>
</body>
</html>
