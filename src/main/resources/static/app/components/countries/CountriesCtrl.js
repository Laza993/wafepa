angular.module("wafepaAppCountriesCtrl", []).controller("countriesCtrl", ['$scope', '$http', function($scope, $http){
    
    var urlAll = "https://restcountries.eu/rest/v2/all";

	var Countries = [];
	$scope.page = [];
	$scope.pageNum = 0;
	$scope.pageSize = 10;
	$scope.totalPages = 0;
	$scope.countries = {};
	$scope.countries.region = "";
	$scope.countries.regBlock = "";

	var getCountries = function(){
		var url = urlAll;
		if($scope.countries.region != ""){
			url = "https://restcountries.eu/rest/v2/region/" + $scope.countries.region;
			console.log(url);
		}
		if($scope.countries.regBlock != ""){
			url = "https://restcountries.eu/rest/v2/regionalbloc/" + $scope.countries.regBlock;
			console.log(url);
		}
		$http.get(url).then(
			function success(res){
				Countries = res.data;	
				var remainder = Countries.length % 10;
				if( remainder != 0){
					$scope.totalPages = (Countries.length - remainder) / 10 + 1;
				}
				if( remainder == 0){
					$scope.totalPages = Countries.length / 10;
				}
				console.log($scope.totalPages);
				
				$scope.pageInitIndex = $scope.pageNum * $scope.pageSize;
				$scope.pageEndIndex = $scope.pageNum * $scope.pageSize + $scope.pageSize;
		
				$scope.page = Countries.slice($scope.pageInitIndex, $scope.pageEndIndex);				
			},
			function error(){
				alert("couldn't find countries");
			}
		)
	}
	getCountries();

	$scope.search = function(){
		$scope.pageNum = 0;
		getCountries();
	}

	$scope.findAll = function(){
		$scope.countries.region = "";
		$scope.countries.regBlock = "";
		$scope.pageNum = 0;
		getCountries();
	}

	$scope.goToPage = function(pageNum){
		$scope.pageNum = pageNum;
		getCountries();
	}

	$scope.changePage = function(direction){
		console.log(direction);
		
		$scope.pageNum += direction;
		console.log($scope.pageNum);
		
		getCountries();
	}
}]);


