var app = angular.module('Transalpes');

app.controller('vehiculoCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {
        var base_url = 'http://arqui201326232.herokuapp.com';
        $scope.contenido = [{"V1": "tmp"}];
        $scope.vehiculo = {};
        $scope.vehiculo.idVehiculo = '1';

        $scope.darVehiculo = function () {
            $window.location.href = base_url + '/vehiculos/' + $scope.vehiculo.idVehiculo;
        };

        $scope.darVehiculos = function () {
            $window.location.href = base_url + '/vehiculos';

        };

        $scope.vehiculo.estado = '';
        $scope.vehiculo.tipo = '';
        $scope.vehiculo.personasPie = '';
        $scope.vehiculo.personasDiscapacitadas = '';
        $scope.vehiculo.linea = '';
        $scope.vehiculo.temperatura = '';
        $scope.vehiculo.hora = 0;
        $scope.vehiculo.panico = false;
        $scope.crearVehiculo = function ()
        {
            alert("Creando vehiculo...");
            $http({
                method: 'POST',
                url: base_url + '/vehiculos',
                data: JSON.stringify($scope.vehiculo),
                headers: {
                    'content-type': 'application/json'
                }
            }).then(function successCallback(response)
            {
                console.log(response);
                alert("Respuesta: " + response.valueOf());

            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log(response);
                alert("Respuesta: " + response.valueOf());
            });


        };

    }]);