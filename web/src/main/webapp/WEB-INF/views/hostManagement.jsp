<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AngularJS ngResource for Host</title>
    <script src="<c:url value='/static/js/lib/angular.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/angular-resource.min.js' />"></script>
    <script src="<c:url value='/static/js/app/host_app.js' />"></script>

    <style>
        .username.ng-valid {
            background-color: lightgreen;
        }
        .username.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .username.ng-dirty.ng-invalid-minlength {
            background-color: yellow;
        }

        .email.ng-valid {
            background-color: lightgreen;
        }
        .email.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .email.ng-dirty.ng-invalid-email {
            background-color: yellow;
        }

    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="HostController as ctrl">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Host Registration Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.host.hostId" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="uname">Name</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.host.hostName" id="uname" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="detail">detail</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.host.hostDetail" id="detail" class="form-control input-sm" placeholder="Enter your detail. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="country">country</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.host.hostCountry" id="country" class="form-control input-sm" placeholder="Enter your country. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="country">state</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.host.hostState" id="state" class="form-control input-sm" placeholder="Enter your state. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="address">Address</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.host.hostAddress" id="address" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="city">city</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.host.hostCity" id="city" class="form-control input-sm" placeholder="Enter your city. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="city">hostPhoneNumber</label>
                        <div class="col-md-7">
                            <input type="number" ng-model="ctrl.host.hostPhoneNumber" id="hostPhoneNumber" class="form-control input-sm" placeholder="Enter your hostPhoneNumber. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="latitude">latitude</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.host.latitude" id="latitude" class="form-control input-sm" placeholder="Enter your latitude. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="longitude">longitude</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.host.longitude" id="longitude" class="form-control input-sm" placeholder="Enter your longitude. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <%--<div class="row">--%>
                    <%--<div class="form-group col-md-12">--%>
                        <%--<label class="col-md-2 control-lable" for="email">Email</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<input type="email" ng-model="ctrl.host.email" id="email" class="email form-control input-sm" placeholder="Enter your Email" required/>--%>
                            <%--<div class="has-error" ng-show="myForm.$dirty">--%>
                                <%--<span ng-show="myForm.email.$error.required">This is a required field</span>--%>
                                <%--<span ng-show="myForm.email.$invalid">This field is invalid </span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl.host.hostId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Host </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID.</th>
                    <th>Name</th>
                    <%--<th>Email</th>--%>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="h in ctrl.hosts">
                    <td><span ng-bind="h.hostId"></span></td>
                    <td><span ng-bind="h.hostName"></span></td>
                    <%--<td><span ng-bind="u.address"></span></td>--%>
                    <%--<td><span ng-bind="u.email"></span></td>--%>
                    <td>
                        <button type="button" ng-click="ctrl.edit(h.hostId)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(h.hostId)" class="btn btn-danger custom-width">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>