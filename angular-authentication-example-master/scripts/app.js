'use strict';

// declare modules
angular.module('Authentication', []);
angular.module('Home', []);
angular.module('vehiculo', []);

angular.module('BasicHttpAuthExample', [
    'Authentication',
    'Home',
    'ngRoute',
    'ngCookies'
])

        .config(['$routeProvider', function ($routeProvider) {

                $routeProvider
                        .when('/login', {
                            controller: 'LoginController',
                            templateUrl: 'modules/authentication/views/login.html'
                        })

                        .when('/home', {
                            controller: 'HomeController',
                            templateUrl: 'modules/home/views/home.html'
                        })

                        .when('/vehiculo', {
                            controller: 'vehiculoCtrl',
                            templateUrl: 'modules/vehiculo/views/vehiculo.html'
                        })

                        .otherwise({redirectTo: '/login'});
            }])

        .run(['$rootScope', '$location', '$cookieStore', '$http',
            function ($rootScope, $location, $cookieStore, $http) {
                // keep user logged in after page refresh
                $rootScope.globals = $cookieStore.get('globals') || {};
                if ($rootScope.globals.currentUser) {
                    $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
                }

                $rootScope.$on('$locationChangeStart', function (event, next, current) {
                    // redirect to login page if not logged in
                    // console.log($rootScope.globals.currentUser.username);
                    if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                        // $location.path('/login');
                    }
                });
            }]);
