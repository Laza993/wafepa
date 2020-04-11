angular.module("wafepaAppEditActivityCtrl", []).controller("EditActivityCtrl",['$scope', '$http', '$location','$routeParams', function($scope, $http, $location, $routeParams){
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
	
}]);