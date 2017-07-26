
var App = angular.module('myApp',['ngResource']);

App.factory('Host', ['$resource', function ($resource) {
    //$resource() function returns an object of resource class
    return $resource(
        'http://localhost:8080/host/:id',
        {id: '@id'},//Handy for update & delete. id will be set with id of instance
        {
            update: {
                method: 'PUT' // To send the HTTP Put request when calling this custom update method.
            }

        }
    );
}]);

App.controller('HostController', ['$scope', 'Host', function($scope, Host) {
    var self = this;
    self.host= new Host();

    self.hosts=[];

    self.fetchAllUsers = function(){
        self.hosts = Host.query();
    };

    self.createUser = function(){
        self.host.$save(function(){
            self.fetchAllUsers();
        });
    };

    self.updateUser = function(){
        self.host.$update(function(){
            self.fetchAllUsers();
        });
    };

    self.deleteUser = function(identity){
        var host = Host.get({id:identity}, function() {
            host.$delete(function(){
                console.log('Deleting host with id ', identity);
                self.fetchAllUsers();
            });
        });
    };

    self.fetchAllUsers();

    self.submit = function() {
        if(self.host.hostId==null){
            console.log('Saving New Host', self.host);
            self.createUser();
        }else{
            console.log('Upddating host with id ', self.host.hostId);
            self.updateUser();
            console.log('Host updated with id ', self.host.hostId);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.hosts.length; i++){
            if(self.hosts[i].hostId === id) {
                self.host = angular.copy(self.hosts[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.host.hostId === id) {//If it is the one shown on screen, reset screen
            self.reset();
        }
        self.deleteUser(id);
    };


    self.reset = function(){
        self.host= new Host();
        $scope.myForm.$setPristine(); //reset Form
    };

    self.showData = function() {
        alert(this.data.foo);
    }

}]);



