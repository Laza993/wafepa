angular.module("wafepaAppActivitiesCtrl", []).controller("ActivityCtrl", ['$scope', '$http', '$location', function($scope, $http, $location){
    
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

}]);
