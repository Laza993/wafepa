angular.module("wafepaAppViewActivityCtrl", []).controller("activityViewCtrl", ['$scope', '$http','$routeParams', function($scope, $http, $routeParams){
	
	var url ="api/activities/" + $routeParams.id;
	
	$scope.Activity = {};

	
	var getActivity = function(){
		$http.get(url).then(
			function success(res){
				$scope.Activity = res.data;
			},
			function error(){
				alert("couldn't find activity");
			}
		)
	}
	getActivity();
}]);