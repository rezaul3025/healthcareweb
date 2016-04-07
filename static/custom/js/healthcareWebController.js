var module = angular.module('healthcareWebApp', ['ui.bootstrap']);
module.controller('HealthcareWebController', ['$http', '$scope', '$window',  function($http, $scope, $window) {
	/*$scope.getSearchKey = function(){
		var key = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];
		
		return key;
	}*/

	$scope.getSearchKey = function(val) {
    return $http.get('getsearchautocomplete', {
      params: {
        queryStr: val
      }
    }).then(function(response){
      	return response.data;
    });
  };

  $scope.getSimpleResult = function(item, model, label, event){
  	 $http({
            method : "GET",
            url : "/simplesearch/",
            params:{queryStr:item}
        }).then(function succes(response) {
            $scope.simpleSearchResults = response.data;
            //alert($scope.simpleSearchResults)
        }, function error(response) {
           // $scope.error = response.statusText;
        });
  };

	
}])