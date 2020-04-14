angular.module("wafepaAppActivitiesCtrl", []).controller("ActivityCtrl", ['$scope', '$http', '$location', function($scope, $http, $location){
    
    var url = "api/activities";

	$scope.activities = [];
	$scope.reverse = false;


	$scope.pageNum = 0;
	$scope.totalPages = 1;

	$scope.pageSize = 5;

	$scope.activityName = "";

	

	$scope.doReverse = function(){
		if($scope.reverse == false){
			$scope.reverse = true;
		}else{
			$scope.reverse = false;
		}
	}

	var getActivities = function(){
		var config = {params : {}};

		config.params.pageNum = $scope.pageNum;
		config.params.pageSize = $scope.pageSize;
		if($scope.activityName != ""){
			config.params.activityName = $scope.activityName;
		}

		$http.get(url, config).then(
			function success(res){
				$scope.activities = res.data;
				$scope.totalPages = res.headers('totalPages');
				
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
		$location.path("/activities/edit/" + id)
	}

	$scope.toAddActivity = function(){
		$location.path('/activities/add');
	}

	$scope.viewActivity = function(id){
		$location.path('/activities/view/' + id);
	}

	$scope.goToPage = function(pageNum){
		$scope.pageNum = pageNum;
		getActivities();
	}

	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getActivities();
	}

	$scope.reload = function(){
		getActivities();
	}

	$scope.searchActivity = function(){
		getActivities();
	}

}]);
