angular.module("wafepaAppUsersCtrl", []).controller("UsersCtrl", ['$scope', '$http', '$location', function($scope, $http, $location){


	// $scope.dataset = userContent.getContent();
    // $scope.header = userContent.getHeader();
    // $scope.subheading = userContent.getSubheader();

	var url = "api/users";
	
	$scope.users = [];
	$scope.name = "";

	$scope.option = "firstName"
	
	$scope.propertyName = "username";
	$scope.reverse = true;
	$scope.pageNum = 0;
	$scope.totalPages = 1;

	$scope.sortBy = function(propertyName){
		$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
		$scope.propertyName = propertyName;
	}

	var ListAllUsers = function(){
		var config = {params : {}};

		if($scope.option == "firstName"){
			config.params.firstName = $scope.name;
		}
		if($scope.option == "lastName"){
			config.params.lastName = $scope.name;
		}
		if($scope.option == "userName"){
			config.params.userName = $scope.name;
		}
		if($scope.option == "email"){
			config.params.email = $scope.name;
		}
		config.params.pageNum = $scope.pageNum;

		$http.get(url, config).then(
			function success(res){
				$scope.users = res.data;
				$scope.totalPages = res.headers("totalPages");
				
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
	
	$scope.searchByName = function(){
		ListAllUsers();
	}

	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		ListAllUsers();
	}

	$scope.goToPage = function(pageNum){
		$scope.pageNum = pageNum;
		ListAllUsers();
	}

}]);
