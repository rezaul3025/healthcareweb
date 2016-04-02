var module = angular.module('Signup', []);
module.controller('SignUpController', ['$http', '$scope', '$window',  function($http, $scope, $window) {
	$scope.doSignup = function(){
		$http({
        	method : "POST",
        	url : "/dosignup/",
        	data:{
        		title : 'dr'
        	}
    	}).then(function mySucces(response) {
        	$scope.myWelcome = response.data;
    	}, function myError(response) {
        	$scope.myWelcome = response.statusText;
    	});
	}
}])