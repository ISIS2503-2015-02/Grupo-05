/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    var app = angular.module('Transalpes', []);


    /*
     * 
     * CLIENTE CONTROLLER
     * 
     */
    app.controller('clienteCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {
            var base_url = 'http://arqui201326232.herokuapp.com';

            $scope.cliente = {};
            $scope.idCliente = '1';


            $scope.darCliente = function () {

                $window.location.href = base_url + '/clientes/' + $scope.idCliente;
            };

            $scope.darClientes = function () {

                $window.location.href = base_url + '/clientes';

            };

            $scope.cliente.tarjetaBancaria = '12345789';
            $scope.cliente.telefono = 0;
            $scope.cliente.name = 'nombre';

            $scope.crearCliente = function ()
            {
                alert("Creando cliente..." + JSON.stringify($scope.cliente));

                $http({
                    method: 'POST',
                    url: base_url + '/clientes',
                    data: JSON.stringify($scope.cliente)


                }).then(function successCallback(response)
                {
                    console.log(response);
                    alert("Respuesta: succes");

                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    console.log(response);
                    alert("Respuesta: error: " + JSON.stringify(response));
                });


            };

        }]);

    /*
     * 
     * RESERVA CONTROLLER
     * 
     */

    app.controller('reservaCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window)
        {
            var base_url = 'http://arqui201326232.herokuapp.com';

            $scope.reserva = {};
            $scope.idReserva = '1';


            $scope.darReservasCliente = function () {

                $window.location.href = base_url + '/clientes/' + $scope.idCliente + '/reservas';
            };

        }]);

    /*
     * 
     * ESTACION CONTROLLER
     * 
     */

    app.controller('estacionCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window)
        {
            var base_url = 'http://arqui201326232.herokuapp.com';

            $scope.estacion = {};
            $scope.idEstacion = '1';


            $scope.darEstacion = function () {

                $window.location.href = base_url + '/estaciones/' + $scope.idEstacion;
            };

            $scope.darDisponibilidadEstacion = function () {

                $window.location.href = base_url + '/estaciones/' + $scope.idEstacion + '/disponibilidad';
            };
            $scope.darEstaciones = function () {


                $window.location.href = base_url + '/estaciones';
            };

        }]);

    /*
     * 
     * INFORME CONTROLLER
     * 
     */

    app.controller('informeCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window)
        {
            var base_url = 'http://arqui201326232.herokuapp.com';


            $scope.darReportes = function () {

                $window.location.href = base_url + '/informes';
            };

            $scope.darReporteGeneral = function () {

                $window.location.href = base_url + '/informes/general';
            };

        }]);
})();
