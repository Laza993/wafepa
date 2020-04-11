angular.module("wafepaAppUsersCtrl", []).controller("UsersCtrl", ['$scope', '$http', '$location', function($scope, $http, $location){


	// $scope.dataset = userContent.getContent();
    // $scope.header = userContent.getHeader();
    // $scope.subheading = userContent.getSubheader();

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
}]);
