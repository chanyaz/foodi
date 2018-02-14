<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Host Management</title>
    <script
            src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script type="text/javascript">
        var app = angular.module('myapp', []);

        app.controller('myappcontroller', function($scope, $http) {
            $scope.hosts = []
            $scope.hostform = {
                name : "",
                department : ""
            };

            getHostDetails();

            function getHostDetails() {
                $http({
                    method : 'GET',
                    url : 'hostdetails'
                }).then(function successCallback(response) {
                    $scope.hosts = response.data;
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            }

            $scope.processHost = function() {
                $http({
                    method : 'POST',
                    url : 'host',
                    data : angular.toJson($scope.hostform),
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then(getHostDetails(), clearForm())
                        .success(function(data){
                            $scope.hosts= data
                        });
            }
            $scope.editHost = function(host) {
                $scope.hostform.name = host.name;
                $scope.hostform.department = host.department;
                disableName();
            }
            $scope.deleteHost = function(host) {
                $http({
                    method : 'DELETE',
                    url : 'deletehost',
                    data : angular.toJson(host),
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then(getHostDetails());
            }

            function clearForm() {
                $scope.hostform.name = "";
                $scope.hostform.department = "";
                document.getElementById("name").disabled = false;
            }
            ;
            function disableName() {
                document.getElementById("name").disabled = true;
            }
        });
    </script>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body ng-app="myapp" ng-controller="myappcontroller">
<h3>Host Registration Form</h3>
<form ng-submit="processHostDetails()">
    <div class="table-responsive">
        <table class="table table-bordered" style="width: 600px">
            <tr>
                <td>Name</td>
                <td><input type="text" id="name" ng-model="hostform.name"
                           size="30" /></td>
            </tr>
            <tr>
                <td>hostCity</td>
                <td><input type="text" id="department"
                           ng-model="hostform.department" size="30" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit"
                                       class="btn btn-primary btn-sm" ng-click="processHost()"
                                       value="Create / Update Host" /></td>
            </tr>
        </table>
    </div>
</form>
<h3>Registered Hosts</h3>
<div class="table-responsive">
    <table class="table table-bordered" style="width: 600px">
        <tr>
            <th>hostName</th>
            <th>hostCity</th>
            <th>Actions</th>
        </tr>

        <tr ng-repeat="host in hosts">
            <td>{{ host.hostName}}</td>
            <td>{{ host.hostCity }}</td>
            <td><a ng-click="editHost(host)" class="btn btn-primary btn-sm">Edit</a>
                | <a ng-click="deleteHost(host)" class="btn btn-danger btn-sm">Delete</a></td>
        </tr>
    </table>
</div>
</body>
</html>