<html>
<body>
<h2>Hello World!</h2>

<div ng-controller="UserController">
  <table border="1">
    <tr ng-repeat="row in rows">
      <td rowspan="2" ng-if="rows.indexOf(row) == '0'">aaa</td>
      <td ng-repeat="column in cols">{{row[column]}}, {{rows.indexOf(row)}}</td>
      <td rowspan="2" ng-if="rows.indexOf(row) == '0'">aaa</td>
    </tr>
  </table>
</div>


</body>
</html>
