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

wafepaApp.controller("countriesCtrl", function($scope, $http){
	var url = "https://restcountries.eu/rest/v2/all";

	$scope.Countries = [];
	$scope.page = [];

	var getCountry = function(){
		$http.get(url).then(
			function success(res){
				$scope.Countries = res.data;
				console.log($scope.Countries);
				console.log(res.headers());
				$scope.page = $scope.Countries.slice(0, 5);
				console.log($scope.page);
							
			},
			function error(){
				alert("couldn't find countries");
			}
		)
	}
	getCountry();


// 	(function() {
//     'use strict';

//     angular
//         .module('app', [])
//         .factory('PagerService', PagerService)
//         .controller('ExampleController', ExampleController);

//     function ExampleController(PagerService) {
//         var vm = this;

//         vm.dummyItems = _.range(1, 151); // dummy array of items to be paged
//         vm.pager = {};
//         vm.setPage = setPage;

//         initController();

//         function initController() {
//             // initialize to page 1
//             vm.setPage(1);
//         }

//         function setPage(page) {
//             if (page < 1 || page > vm.pager.totalPages) {
//                 return;
//             }

//             // get pager object from service
//             vm.pager = PagerService.GetPager(vm.dummyItems.length, page);

//             // get current page of items
//             vm.items = vm.dummyItems.slice(vm.pager.startIndex, vm.pager.endIndex + 1);
//         }
//     }

//     function PagerService() {
//         // service definition
//         var service = {};

//         service.GetPager = GetPager;

//         return service;

//         // service implementation
//         function GetPager(totalItems, currentPage, pageSize) {
//             // default to first page
//             currentPage = currentPage || 1;

//             // default page size is 10
//             pageSize = pageSize || 10;

//             // calculate total pages
//             var totalPages = Math.ceil(totalItems / pageSize);

//             var startPage, endPage;
//             if (totalPages <= 10) {
//                 // less than 10 total pages so show all
//                 startPage = 1;
//                 endPage = totalPages;
//             } else {
//                 // more than 10 total pages so calculate start and end pages
//                 if (currentPage <= 6) {
//                     startPage = 1;
//                     endPage = 10;
//                 } else if (currentPage + 4 >= totalPages) {
//                     startPage = totalPages - 9;
//                     endPage = totalPages;
//                 } else {
//                     startPage = currentPage - 5;
//                     endPage = currentPage + 4;
//                 }
//             }

//             // calculate start and end item indexes
//             var startIndex = (currentPage - 1) * pageSize;
//             var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

//             // create an array of pages to ng-repeat in the pager control
//             var pages = _.range(startPage, endPage + 1);

//             // return object with all pager properties required by the view
//             return {
//                 totalItems: totalItems,
//                 currentPage: currentPage,
//                 pageSize: pageSize,
//                 totalPages: totalPages,
//                 startPage: startPage,
//                 endPage: endPage,
//                 startIndex: startIndex,
//                 endIndex: endIndex,
//                 pages: pages
//             };
//         }
//     }
// })();




});


wafepaApp.controller("activityViewCtrl", function($scope, $http, $routeParams){
	
	var url ="api/activities/" + $routeParams.id;
	
	$scope.Activity = {};

	console.log(url);
	
	var getActivity = function(){
		$http.get(url).then(
			function success(res){
				$scope.Activity = res.data;
				console.log($scope.Activity);
			},
			function error(){
				alert("couldn't find activity");
			}
		)
	}
	getActivity();
});

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

	$scope.doReverse = function(){
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

	$scope.viewActivity = function(id){
		$location.path('/activities/view/' + id);
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





