(function () {
    var app = angular.module('aplicacionMundial');

    /*
     * 
     * ESTACION CONTROLLER
     * 
     */

    app.controller('estacionCtrl', ['$scope', '$http', '$window','$rootScope', function ($scope, $http, $window, $rootScope)
        {
            var base_url = 'http://localhost:8083/webresources';

            $scope.estacion = {};
            $scope.idEstacion = '1';


            $scope.darEstacion = function () {

                $window.location.href = base_url + '/estaciones/' + $scope.idEstacion;
            };

            $scope.darDisponibilidadEstacion = function () {

                $window.location.href = base_url + '/estaciones/' + $scope.idEstacion + '/disponibilidad';
            };
            $scope.darEstaciones = function () {
                
                $http({
                    method: 'GET',
                    url: base_url+'/estaciones',
                   headers: {'Authorization': 'Basic ZXN0YWNpb25Vc2VyOkVzdGFjaW9uMTIz'}
                }).then(function successCallback(response)
                {
                    // this callback will be called asynchronously
                    // when the response is available
                    alert(JSON.stringify(response.data));
                }, function errorCallback(response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    alert(JSON.stringify(response));
                });

            };

        }]);

})();



