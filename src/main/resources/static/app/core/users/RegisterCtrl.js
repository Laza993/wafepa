angular.module("wafepaAppRegisterCtrl", []).controller("RegisterCtrl", ['$scope', '$http', '$location', function($scope, $http, $location){
    
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

}]);