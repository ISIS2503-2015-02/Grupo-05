/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    var app = angular.module('app', ['angularUtils.directives.dirPagination', 'ngCookies']);
    var BASE_URL = 'http://localhost:8083/webresources/';
    var HOME = 'http://localhost:8083';


    //------------------------------------------------------------------------
    // DIRECTIVAS
    //------------------------------------------------------------------------

    app.directive('navbar', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/navbar.html',
            controller: 'navbarCtrl',
            controllerAs: 'toolbar'
        };
    });

    app.directive('loginForm', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/login-form.html',
            controller: 'loginCtrl'
        };
    });

    app.directive('estacion', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/estacion.html',
            controller: 'estacionCtrl'
        };
    });

    app.directive('vehiculo', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/vehiculo.html'
        };
    });

    app.directive('cliente', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/cliente.html'
        };
    });

    app.directive('admin', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/admin.html'
        };
    });
    //------------------------------------------------------------------------
    // CONTROLADORES
    //------------------------------------------------------------------------

    app.controller('navbarCtrl', ['$scope', '$http', '$window', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $http, $window, $rootScope, $location, AuthenticationService) {

            $scope.show = function (role)
            {
                var user = AuthenticationService.currentUser();
                return user ? user.role === role : false;
                // return true;
            };

            $scope.cerrarSesion = function ()
            {

            };

            $scope.logged = function ()
            {
                return AuthenticationService.currentUser();
            };

            $scope.logout = function ()
            {
                alert("Logged out");
                AuthenticationService.ClearCredentials();
                $window.location.href = HOME;
            };

        }]);


    app.controller('loginCtrl', ['$scope', '$http', '$window', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $http, $window, $rootScope, $location, AuthenticationService) {

            $scope.currentUser = {
                user: 'user',
                password: 'pass',
                role: ''
            };


            showError = false;

            $scope.showError = function ()
            {
                return showError;
            };
            $scope.error = "Credenciales invalidas";

            $scope.login = function ()
            {

                $scope.dataLoading = true;
                console.log("Loggeando: " + JSON.stringify($scope.currentUser));
                AuthenticationService.login($scope.currentUser.user, $scope.currentUser.password,
                        function (response)
                        {
                            if (response.success)
                            {
                                console.log("Data: " + JSON.stringify(response.data));
                                $scope.currentUser.role = response.data.role;
                                console.log("Current user in loginCtrl: " + JSON.stringify($scope.currentUser));
                                AuthenticationService.SetCredentials($scope.currentUser.user, $scope.currentUser.password, $scope.currentUser.role);
                            }
                        });


            };

        }]);


    app.controller('estacionCtrl', ['$scope', '$http', '$window', '$rootScope', 'AuthenticationService', function ($scope, $http, $window, $rootScope, AuthenticationService)
        {
            $scope.estacion =
                    {
                        capacidad: "1",
                        latitud: 0,
                        longitud: 0
                    };
            $scope.idEstacion = 0;
            $scope.idVcub = 0;
            $scope.idUsuario = 0;
            $scope.currentPage = 1;
            $scope.pageSize = 10;
            $scope.idVehiculo = 0;
            $scope.estaciones = [];
            $scope.vcubs = [];


            $scope.cargar = function ()
            {
                $http({
                    method: 'GET',
                    url: BASE_URL + 'estaciones'
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(JSON.stringify(response.data));
                    $scope.estaciones = response.data;
                    
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                });
            };

            $scope.cargarVcubs = function ()
            {
                var user = AuthenticationService.currentUser();
                $http({
                    method: 'GET',
                    url: BASE_URL + 'estaciones/' + 1 //+user.username
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(JSON.stringify(response.data));
                    $scope.vcubs = response.data.vcubs;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                });
            };

            $scope.cargar();

            $scope.agregarEstacion = function ()
            {
                console.log("Agregando estacion: " + JSON.stringify($scope.estacion));
                $http({
                    method: 'POST',
                    url: BASE_URL + 'estaciones',
                    data: $scope.estacion
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(JSON.stringify(response.data));
                    $scope.cargar();
                    alert("Se ha agregado correctamente la estacion.");
                     $window.location.href = HOME;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                    alert("Se ha producido un error.");
                });
            };

            $scope.registrarVcub = function ()
            {
                var data = {"vcub_id": $scope.idVcub};
                console.log("Registrando vcub:" + JSON.stringify(data) + " en la estacion " + JSON.stringify($scope.idEstacion));
                $http({
                    method: 'POST',
                    url: BASE_URL + 'estaciones/' + JSON.stringify($scope.idEstacion) + '/vcubs',
                    data: data
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(JSON.stringify(response.data));
                    $scope.cargarVcubs();
                    alert("Se ha agregado correctamente el vcub");
                     $window.location.href = HOME;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                    alert("Se ha producido un error.");
                });
            };

            $scope.prestarVcub = function ()
            {
                var data =
                        {
                            "cliente_id": $scope.idUsuario,
                            "vcub_id": $scope.idVehiculo
                        };
                console.log("cliente_id: " + $scope.idUsuario);
                console.log("vcub_id: " + $scope.idVehiculo);
                console.log("Enviado peticion de reserva:" + JSON.stringify(data));
                $http({
                    method: 'POST',
                    url: BASE_URL + 'estaciones/' + JSON.stringify($scope.idEstacion) + '/reservas',
                    data: data
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(JSON.stringify(response.data));
                    $scope.cargarVcubs();
                    alert("Se ha agregado correctamente el vcub");
                     $window.location.href = HOME;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                    alert("Se ha producido un error.");
                });
            };

        }]);

    app.controller('vehiculoCtrl', ['$scope', '$http', '$window', '$rootScope', function ($scope, $http, $window, $rootScope)
        {
            $scope.vehiculo = {
                tipo: "Vcub",
                estado: "estado",
                color: "color",
                personasPie: 1,
                personasDiscapacitadas: 1,
                linea: "A"
            };
            $scope.idVehiculo = '1';
            $scope.currentPage = 1;
            $scope.pageSize = 10;
            $scope.vehiculos =
                    [
                    ];

            $scope.cargar = function ()
            {
                $http({
                    method: 'GET',
                    url: BASE_URL + 'vehiculos'
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(JSON.stringify(response.data));
                    $scope.vehiculos = response.data;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                });

            };

            $scope.cargar();

            $scope.agregarVehiculo = function ()
            {
                console.log("Agregando Vehiculo: " + JSON.stringify($scope.vehiculo));
                $http({
                    method: 'POST',
                    url: BASE_URL + 'vehiculos',
                    data: $scope.vehiculo
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    $scope.cargar();
                    alert("Se ha agregado correctamente el vehiculo.");
                     $window.location.href = HOME;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                    alert("Se ha producido un error.");
                });
            };

        }]);


    app.controller('clienteCtrl', ['$scope', '$http', '$window', '$rootScope', function ($scope, $http, $window, $rootScope)
        {
            $scope.cliente = {
                id: "1",
                email: "user",
                password: "",
                name: "nombre",
                telefono: "12345",
                tarjetaBancaria: "67890"
            };

            $scope.currentPage = 1;
            $scope.pageSize = 10;
            $scope.clientes =
                    [
                    ];
            $scope.reservas = [
            ];

            $scope.cargar = function ()
            {
                $http({
                    method: 'GET',
                    url: BASE_URL + 'clientes'
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(JSON.stringify(response.data));
                    $scope.clientes = response.data;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                });

            };

            $scope.cargar();

            $scope.agregarCliente = function ()
            {
                console.log("Agregando Cliente: " + JSON.stringify($scope.cliente));
                $http({
                    method: 'POST',
                    url: BASE_URL + 'clientes',
                    data: $scope.cliente
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    $scope.cargar();
                    alert("Se ha agregado correctamente el cliente.");
                     $window.location.href = HOME;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                    alert("Se ha producido un error.");
                });
            };

            $scope.cargarReservas = function ()
            {
                $http({
                    method: 'GET',
                    url: BASE_URL + 'clientes/1/reservas'
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(JSON.stringify(response.data));
                    $scope.reservas = response.data;
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(JSON.stringify(response));
                });
            };

            $scope.cargarReservas();

        }]);



    app.factory('Base64', function () {
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
    app.factory('AuthenticationService',
            ['Base64', '$http', '$rootScope', '$timeout', '$cookieStore',
                function (Base64, $http, $rootScope, $timeout, $cookieStore) {
                    var service = {};
                    service.login = function (username, password, callback) {

                        $http({
                            method: 'POST',
                            url: BASE_URL + 'users/login',
                            data: {
                                userName: username, //"estacionUser",
                                password: password //"Estacion123"
                            }
                        }).then(function successCallback(response)
                        {
                            response.success = true;
                            console.log(JSON.stringify(response));
                            callback(response);
                        }, function errorCallback(response)
                        {
                            console.log("Failed: " + JSON.stringify(response));
                            response.success = false;
                            response.message = "Credenciales invalidas";
                            callback(response);
                        });
                    };
                    service.SetCredentials = function (username, password, role)
                    {
                        var authdata = Base64.encode(username + ':' + password);

                        $rootScope.globals = {
                            currentUser: {
                                username: username,
                                authdata: authdata,
                                role: role
                            }
                        };

                        console.log("Setting credentials..." + JSON.stringify($rootScope.globals.currentUser));
                        $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
                        $cookieStore.put('globals', $rootScope.globals);

                    };
                    service.ClearCredentials = function () {
                        $rootScope.globals = {};
                        $cookieStore.remove('globals');
                        $http.defaults.headers.common.Authorization = 'Basic ';
                    };

                    service.currentUser = function ()
                    {
                        return $rootScope.globals && $rootScope.globals.currentUser;
                    };

                    return service;
                }]);


    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http', '$window'];
    function run($rootScope, $location, $cookieStore, $http, $window) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser)
        {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        $rootScope.onLogin = false;
        $rootScope.$on('$locationChangeStart', function (event, next, current)
        {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/registrar']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn)
            {
                $location.path('/login');
            }
            else if (loggedIn && !restrictedPage)
                $location.path('/home');
        });
    }
    ;

    app.run(run);

})();


