/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    var app = angular.module('Transalpes', []);


    var base_url = 'http://arqui201326232.herokuapp.com';


    /*
     * 
     * CLIENTE CONTROLLER
     * 
     */

    /*
     * 
     * RESERVA CONTROLLER
     * 
     */

    app.controller('reservaCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window)
        {
            //var base_url = 'http://arqui201326232.herokuapp.com';

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
            //var base_url = 'http://arqui201326232.herokuapp.com';

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
            //var base_url = 'http://arqui201326232.herokuapp.com';


            $scope.darReportes = function () {

                $window.location.href = base_url + '/informes';
            };

            $scope.darReporteGeneral = function () {

                $window.location.href = base_url + '/informes/general';
            };

        }]);
})();
