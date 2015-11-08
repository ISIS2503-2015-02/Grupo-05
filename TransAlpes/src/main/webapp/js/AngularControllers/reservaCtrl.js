(function(){var app= angular.module('aplicacionMundial');
    
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
    
})();



