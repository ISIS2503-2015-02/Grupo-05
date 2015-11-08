/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    var aplicacionMundial = angular.module('aplicacionMundial', []);
    aplicacionMundial.directive('toolbar', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/toolbar.html',
            controller: function () {
                this.tab = 0;
                this.selectTab = function (setTab) {
                    this.tab = setTab;
                };
                this.isSelected = function (tabParam) {
                    return this.tab === tabParam;
                };
            },
            controllerAs: 'toolbar'
        };
    });
    aplicacionMundial.directive('competitorInfo', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/competitor-info.html',
            controller: 'getCompetitors'
        };
    });
    aplicacionMundial.controller("getCompetitors", function ($http, $scope) {
        $http.get('http://localhost:8080/competitors/get').
                success(function (data, status, headers, config) {
                    $scope.competitors = data;
                }).
                error(function (data, status, headers, config) {
                    // log error
                });
    });
    aplicacionMundial.directive('estacion', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/estacion-form.html',
            controller: 'estacionCtrl'
        };
    });
    aplicacionMundial.directive('informe', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/informe-form.html',
            controller: 'informeCtrl'
        };
    });
    aplicacionMundial.directive('cliente', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/cliente-form.html',
            controller: 'clienteCtrl'
        };
    });
    aplicacionMundial.directive('vehiculo', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/vehiculo-form.html',
            controller: 'vehiculoCtrl'
        };
    });
    aplicacionMundial.controller("competitorCtrl", function ($http, $scope) {

        $scope.addCompetitor = function () {
            console.log('name');
            $http.post('http://localhost:8080/competitors/add', JSON.stringify($scope.competitor)).success(function (data, headers) {
                $scope.competitor = {};
                $scope.toolbar.selectTab(2);
            });
        };
        $scope.login = function () {
            console.log('name');
            $http.post('http://localhost:8080/competitors/login', JSON.stringify($scope.competitor)).success(function (data, headers) {
                $scope.toolbar.selectTab(2);
            });
        };
    });
    aplicacionMundial.directive('loginForm', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/login-form.html',
            controller: 'loginCtrl'
        };
    });
    aplicacionMundial.factory('Base64', function () {
        /* jshint ignore:start */

        var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
        return {
            encode: function (input) {
                var output = "";
                var chr1, chr2, chr3 = "";
                var enc1, enc2, enc3, enc4 = "";
                var i = 0;
                do {
                    chr1 = input.charCodeAt(i++);
                    chr2 = input.charCodeAt(i++);
                    chr3 = input.charCodeAt(i++);
                    enc1 = chr1 >> 2;
                    enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                    enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                    enc4 = chr3 & 63;
                    if (isNaN(chr2)) {
                        enc3 = enc4 = 64;
                    } else if (isNaN(chr3)) {
                        enc4 = 64;
                    }

                    output = output +
                            keyStr.charAt(enc1) +
                            keyStr.charAt(enc2) +
                            keyStr.charAt(enc3) +
                            keyStr.charAt(enc4);
                    chr1 = chr2 = chr3 = "";
                    enc1 = enc2 = enc3 = enc4 = "";
                } while (i < input.length);
                return output;
            },
            decode: function (input) {
                var output = "";
                var chr1, chr2, chr3 = "";
                var enc1, enc2, enc3, enc4 = "";
                var i = 0;
                // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
                var base64test = /[^A-Za-z0-9\+\/\=]/g;
                if (base64test.exec(input)) {
                    window.alert("There were invalid base64 characters in the input text.\n" +
                            "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                            "Expect errors in decoding.");
                }
                input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
                do {
                    enc1 = keyStr.indexOf(input.charAt(i++));
                    enc2 = keyStr.indexOf(input.charAt(i++));
                    enc3 = keyStr.indexOf(input.charAt(i++));
                    enc4 = keyStr.indexOf(input.charAt(i++));
                    chr1 = (enc1 << 2) | (enc2 >> 4);
                    chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                    chr3 = ((enc3 & 3) << 6) | enc4;
                    output = output + String.fromCharCode(chr1);
                    if (enc3 != 64) {
                        output = output + String.fromCharCode(chr2);
                    }
                    if (enc4 != 64) {
                        output = output + String.fromCharCode(chr3);
                    }

                    chr1 = chr2 = chr3 = "";
                    enc1 = enc2 = enc3 = enc4 = "";
                } while (i < input.length);
                return output;
            }
        };
        /* jshint ignore:end */
    });
    aplicacionMundial.factory('AuthenticationService',
            ['Base64', '$http', '$rootScope', '$timeout',
                function (Base64, $http, $rootScope, $timeout) {
                    var service = {};
                    service.Login = function (username, password, callback) {

                        console.log(username + " " + password);
                        //$http.post('http://localhost:8083/webresources/users/login', {"userName": username, "password": password})
                        //      .success(function (response)
                        //    {
                        //      console.log("Succes");
                        //    response.success = true;
                        //  callback(response);
                        // });


                        $http({
                            method: 'POST',
                            url: 'http://localhost:8083/webresources/users/login',
                            data: {
                                userName: username,//"estacionUser",
                                password: password //"Estacion123"
                            }
                        }).then(function successCallback(response)
                        {
                            response.success = true;
                            console.log(JSON.stringify(response));
                            callback(response);
                        }, function errorCallback(response)
                        {
                            console.log("Failed");
                            response.success = false;
                            response.message = "Credenciales invalidas";
                            callback(response);
                        });
                    };
                    service.SetCredentials = function (username, password)
                    {
                        var authdata = Base64.encode(username + ':' + password);
                        console.log("Setting credentials..."+authdata);
                        $rootScope.globals = {
                            currentUser: {
                                username: username,
                                authdata: authdata
                            }
                        };
                        $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line

                    };
                    service.ClearCredentials = function () {
                        $rootScope.globals = {};
                        $http.defaults.headers.common.Authorization = 'Basic ';
                    };
                    return service;
                }]);
})();


