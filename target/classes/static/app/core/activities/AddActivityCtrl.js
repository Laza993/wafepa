angular.module("wafepaAppAddActivityCtrl", []).controller("AddActivityCtrl", ['$scope', '$http', '$location', function($scope, $http, $location){
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
}]);