var wafepaApp = angular.module("wafepaApp", ["ngRoute"]);

wafepaApp.config(['$routeProvider', function($routeProvider) {
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
			templateUrl : '/app/html/Users.html'
		})
		.when('/users/register', {
			templateUrl : '/app/html/Register.html'
		})
		.when('/login', {
			templateUrl : '/app/html/Login.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);

wafepaApp.controller("RegisterCtrl", function($scope, $http, $location){
	var url ="api/users";

	$scope.user = {};
	$scope.user.username = "";
	$scope.user.email = "";
	$scope.user.firstName = "";
	$scope.user.lastName = "";
	$scope.user.password1 = "";
	$scope.user.password2 = "";

	$scope.Register = function(){
		if($scope.user.username == "" || $scope.user.email == "" || $scope.user.firstName == "" || $scope.user.lastName == "" || $scope.user.password1 == "" || $scope.user.password2 == ""){
			alert("all fields are required")
			return false;
		}
		if($scope.user.password1 != $scope.user.password2){
			alert("Your password and confirmation password do not match.")
			return false;
		}
		$http.post(url, $scope.user).then(
			function success(res){
				$location.path("/users");
			},
			function error(){
				alert("failed to create user");
			}
		)
	}

});

wafepaApp.controller("UsersCtrl", function($scope, $http, $location){
	
	var url = "api/users";
	
	$scope.users = [];
	
	$scope.propertyName = "username";
	$scope.reverse = true;

	$scope.sortBy = function(propertyName){
		$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
		$scope.propertyName = propertyName;
	}

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

	$scope.goToRegister = function(){
		$location.path('/users/register');
	}


	$scope.deleteUser = function(id){
		var urlDel = "api/users/" + id;
		$http.delete(urlDel).then(
			function success(res){
				console.log(res);
				ListAllUsers();
			}, function error(){
				alert("failed to delete user");
			}
		)
	}
});


wafepaApp.controller("ActivityCtrl", function($scope, $http, $location){
	var url = "api/activities";

	$scope.activities = [];
	$scope.reverse = false;

	$scope.reverseChange = function(){
		if($scope.reverse == false){
			$scope.reverse = true;
		}else{
			$scope.reverse = false;
		}
	}

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





