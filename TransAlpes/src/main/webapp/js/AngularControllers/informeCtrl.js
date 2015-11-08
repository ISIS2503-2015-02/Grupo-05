(function(){var app= angular.module('aplicacionMundial');
    
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



