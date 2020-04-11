angular.module("wafepaAppRoutes", []).config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
            templateUrl : '/app/html/home.html'
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/view/:id', {
			templateUrl : '/app/html/viewActivity.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/addActivity.html'
		})
		.when('/activities/edit/:id', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.when('/users', {
            templateUrl : '/app/html/Users.html',
		})
		.when('/users/register', {
			templateUrl : '/app/html/Register.html'
		})
		.when('/login', {
			templateUrl : '/app/html/Login.html'
		})
		.when('/activities/view/:id', {
			templateUrl : '/app/html/viewActivity.html'
		})
		.when('/countries', {
			templateUrl : '/app/html/countries.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
