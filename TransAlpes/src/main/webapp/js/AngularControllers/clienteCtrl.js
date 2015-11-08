(function(){var app= angular.module('aplicacionMundial');
    
     /*
     * 
     * CLIENTE CONTROLLER
     * 
     */
    app.controller('clienteCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {
            // var base_url = 'http://arqui201326232.herokuapp.com';

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
    
})();



