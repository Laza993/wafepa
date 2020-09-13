angular.module("moviePageCtrl", []).controller("PageCtrl", ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http){

    var url = "https://api.themoviedb.org/3";
    var api_key = "?api_key=0cfc8693d7525c6ae845849148249b19";
    var searchMovie = "/movie/" + $routeParams.idMp;

    // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
     
    var urlMovie = url + searchMovie + api_key

    $scope.movie = {};

    var getMovie = function(){
        
     $http.get(urlMovie).then(
            function success(res){              
                 $scope.movie = res.data;
                 console.log($scope.movie);
            },
            function error(){
                console.log("some error");
            }
        )
    }
    getMovie();
    

}]);