<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 7/12/2017
  Time: 10:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

    <script src="<c:url value="/static/js/lib/angular.min.js"/>"></script>
    <script src="<c:url value="/static/js/lib/angular-resource.min.js"/>"></script>
    <script src="<c:url value="/static/js/lib/ng-map.min.js"/>"></script>
    <%--<script src="<c:url value="/static/js/app/hostAccess_map.js"/>"></script>--%>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD9kOrSAog9xPLswbLnDaPB_djHHqF0EK8"></script>


    <script>
        var App = angular.module('ngMap',['ngResource']);

        App.factory('HostAccess', ['$resource', function ($resource) {
            //$resource() function returns an object of resource class
            return $resource(
                    'http://localhost:8080/hostAccess/:id',
                    {id: '@id'},//Handy for update & delete. id will be set with id of instance
                    {
                        update: {
                            method: 'PUT' // To send the HTTP Put request when calling this custom update method.
                        }

                    }
            );
        }]);

        App.controller('MyCtrl', ['$scope', 'HostAccess', function($scope, HostAccess) {
            var vm = this;
//            self.hostAccesses= new HostAccess();
//
//            self.hostAccesses=[];
//
//            self.fetchAllUsers = function(){
//                self.hostAccesses = HostAccess.query();
//            };
//
//            //self.createUser = function(){
//            //    self.host.$save(function(){
//            //        self.fetchAllUsers();
//            //    });
//            //};
//            //
//            //self.updateUser = function(){
//            //    self.host.$update(function(){
//            //        self.fetchAllUsers();
//            //    });
//            //};
//            //
//            //self.deleteUser = function(identity){
//            //    var host = Host.get({id:identity}, function() {
//            //        host.$delete(function(){
//            //            console.log('Deleting host with id ', identity);
//            //            self.fetchAllUsers();
//            //        });
//            //    });
//            //};
//
//            self.fetchAllUsers();
//
//            //self.submit = function() {
//            //    if(self.host.hostId==null){
//            //        console.log('Saving New Host', self.host);
//            //        self.createUser();
//            //    }else{
//            //        console.log('Upddating host with id ', self.host.hostId);
//            //        self.updateUser();
//            //        console.log('Host updated with id ', self.host.hostId);
//            //    }
//            //    self.reset();
//            //};
//            //
//            //self.edit = function(id){
//            //    console.log('id to be edited', id);
//            //    for(var i = 0; i < self.hosts.length; i++){
//            //        if(self.hosts[i].hostId === id) {
//            //            self.host = angular.copy(self.hosts[i]);
//            //            break;
//            //        }
//            //    }
//            //};
//            //
//            //self.remove = function(id){
//            //    console.log('id to be deleted', id);
//            //    if(self.host.hostId === id) {//If it is the one shown on screen, reset screen
//            //        self.reset();
//            //    }
//            //    self.deleteUser(id);
//            //};
//            //
//            //
//            //self.reset = function(){
//            //    self.host= new Host();
//            //    $scope.myForm.$setPristine(); //reset Form
//            //};
//
//            self.showData = function() {
//                alert(this.data.foo);
//            }

        }]);

    </script>
</head>

<body ng-app="ngMap">
<div ng-controller="MyCtrl as vm">
    <ng-map zoom="11" center="[40.74, -74.18]">
        <%--<marker ng-repeat="p in vm.positions"--%>
                <%--position="{{p.pos}}"--%>
                <%--data="{{data[$index]}}"--%>
                <%--on-click="showData()";--%>
                <%--title="pos: {{p.pos}}"></marker>--%>
    </ng-map>
</div>
</body>
</html>

