(function () {
    var app = angular.module('aplicacionMundial');


    app.controller('loginCtrl', ['$scope', '$http', '$window', '$rootScope', '$location', 'AuthenticationService',
        function ($scope, $http, $window, $rootScope, $location, AuthenticationService) {
            var base_url = 'http://localhost:8083/webresources';

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
                /*
                 $scope.dataLoading = true;
                 $http({
                 method: 'POST',
                 url: 'http://localhost:8083/webresources/users/login',
                 data: {"userName": $scope.currentUser.user, "password": $scope.currentUser.password}
                 }).then(function successCallback(response)
                 {
                 response.success = true;
                 $scope.dataLoading = false;
                 showError = false;
                 alert("Logged in:\n"+JSON.stringify(response));
                 }, function errorCallback(response)
                 {
                 console.log("Error: "+JSON.stringify(response));
                 $scope.dataLoading = false;
                 showError = true;
                 $scope.currentUser.password = "";
                 $scope.currentUser.user = "";
                 });
                 */
       
                $scope.dataLoading = true;
                AuthenticationService.Login($scope.currentUser.user, $scope.currentUser.password, function (response)
                {
                    if (response.success)
                    {
                        AuthenticationService.SetCredentials($scope.currentUser.user, $scope.currentUser.password);
                        $scope.dataLoading = false;
                        showError = false;
                        alert("Logeado");
                        //$location.path('/home');
                    } else {
                        $scope.dataLoading = false;
                        showError = true;
                      
                    }
                });

            };

        }]);

})();



