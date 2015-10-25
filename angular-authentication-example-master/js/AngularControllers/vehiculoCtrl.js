
var app = angular.module('Transalpes');

app.controller('vehiculoCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {
        var base_url = 'http://arqui201326232.herokuapp.com';

        $scope.vehiculo = {};
        $scope.idVehiculo = '1';


        $scope.darVehiculo = function () {
              $window.location.href = base_url + '/vehiculos/' + $scope.idVehiculo;
        };

        $scope.darVehiculos = function () {
            $window.location.href = base_url + '/vehiculos';

        };

        $scope.vehiculo.estado = 'Activo';
        $scope.vehiculo.tipo = 'Vcub';
        $scope.vehiculo.personasPie = '50';
        $scope.vehiculo.personasDiscapacitadas = '10';
        $scope.vehiculo.linea = 'A';
        $scope.vehiculo.temperatura = 20;
        $scope.vehiculo.hora = 0;
        $scope.vehiculo.panico = false;
        $scope.crearVehiculo = function ()
        {
            alert("Creando vehiculo..." + JSON.stringify($scope.vehiculo));

            $http({
                method: 'POST',
                url: base_url + '/vehiculos',
                data: JSON.stringify($scope.vehiculo)


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