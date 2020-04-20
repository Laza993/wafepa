angular.module("moviesController", []).controller("moviesCtrl", ['$http', '$scope', '$location', function($http, $scope, $location){
    console.log("welcome to movies page")

    var url = "https://api.themoviedb.org/3";
    var api_key = "?api_key=0cfc8693d7525c6ae845849148249b19";

    $scope.movieName = "";
    $scope.movieRelaceYear = "";
    $scope.movies = [];
    $scope.page = 1;

    var queryName = "&query=";
    var queryYear = "&year=";
    var searchMovie = "/search/movie"




   $scope.searchForMovie = function(){

        queryName +=  $scope.movieName;
        queryYear += $scope.movieRelaceYear;
        var urlSearch = "";
        if($scope.movieName == "" && $scope.movieRelaceYear == ""){
            alert("name field is reqired")
            return;
        }
        if($scope.movieName == ""){
            alert("name field is reqired")
            return;
        }
        if($scope.movieRelaceYear == ""){
            urlSearch = url + searchMovie + api_key + "&language=en-US" + queryName + "&page=" + $scope.page + "&include_adult=false";
        }

        urlSearch = url + searchMovie + api_key + "&language=en-US" + queryName + "&page=" + $scope.page + "&include_adult=false" + queryYear;
        getMovies(urlSearch);
        queryName = "&query=";
        queryYear = "&year=";        
   }

   

   var getMovies = function(url1){
       console.log(url1);
       
    $http.get(url1).then(
           function success(res){              
                $scope.movies = res.data;
                console.log($scope.movies);
           },
           function error(){
               console.log("some error");
           }
       )
   }

}]);