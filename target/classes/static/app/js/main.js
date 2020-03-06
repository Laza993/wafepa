var wafepaApp = angular.module("wafepaApp", ["ngRoute"]);

wafepaApp.controller("HomeCtrl", function($scope, $location){
	
	$scope.toActivities = function(){
		$location.path("activities");
	}

	$scope.toUsers = function(){
		$location.path("users");
	}
});


wafepaApp.controller("ActivityCtrl", function($scope, $http, $location){
	var url = "api/activities";

	$scope.activities = [];

	var getActivities = function(){
		$http.get(url).then(
			function success(res){
				//console.log(res)
				$scope.activities = res.data;
			},
			function error(){
				alert("failed to fetch all activities")
			}
		)
	}
	getActivities();

	$scope.doDeleteActivity = function(id){
		var url1 = "api/activities/" + id;
		$http.delete(url1).then(
			function success(){
				getActivities();
			},
			function error(){
				alert("deletition failed")
			}
		)
	}


	$scope.toEditActivity = function(id){
		console.log(id)
		$location.path("/activities/edit/" + id)
	}

	$scope.toAddActivity = function(){
		$location.path('/activities/add');
	}

});

wafepaApp.controller("EditActivityCtrl", function($scope, $http, $location, $routeParams){
	console.log($routeParams.id);

	var url = "api/activities/" + $routeParams.id;

	$scope.Activity = {};
	$scope.Activity.name = "";

	var FindActivity = function(){
		$http.get(url).then(
			function success(res){
				console.log(res);
				$scope.Activity = res.data;
			},
			function error(){
				alert("couldn't fetch Activity");
			}
		)
	}
	FindActivity();

	$scope.doEdit = function(){
		$http.put(url, $scope.Activity).then(
			function success(){
				$location.path("/activities")
			},
			function error(){
				alert("Unsuccessful edit")
			}
		)
	}

	
});

wafepaApp.controller("AddActivityCtrl", function($scope, $http, $location){
	var url = "api/activities/";

	$scope.Activity = {};
	$scope.Activity.name = "";

	$scope.createActivity = function(){
		$http.post(url, $scope.Activity).then(
			function success(){
				console.log($scope.Activity)
				$location.path('/activities')
			},
			function error(){
				alert("Creation failed")
			}
		)
	}

});


wafepaApp.controller("UsersCtrl", function($scope, $http){
	var url = "api/users";

	$scope.users = [];

	var ListAllUsers = function(){
		$http.get(url).then(
			function success(res){
				console.log(res);
				$scope.users = res.data;
			},
			function error(){
				alert("failed to fetch users")
			}
		)
	}
	ListAllUsers();
});


wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/addActivity.html'
		})
		.when('/activities/edit/:id', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.when('/users', {
			templateUrl : '/app/html/Users.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);