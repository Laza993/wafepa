angular.module("wafepaAppRoutes", []).config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
            templateUrl : '/app/html/home.html'
		})
		.when('/activities', {
			templateUrl : '/app/core/activities/activities.html'
		})
		.when('/activities/view/:id', {
			templateUrl : '/app/core/activities/viewActivity.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/core/activities/addActivity.html'
		})
		.when('/activities/edit/:id', {
			templateUrl : '/app/core/activities/edit-activity.html'
		})
		.when('/users', {
            templateUrl : '/app/core/users/Users.html',
		})
		.when('/users/register', {
			templateUrl : '/app/core/users/Register.html'
		})
		.when('/login', {
			templateUrl : '/app/core/users/Login.html'
		})
		.when('/countries', {
			templateUrl : '/app/core/countries/countries.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);

