var module = angular.module('Signup', []);
module.controller('SignUpController', ['$http', '$scope', '$window',  function($http, $scope, $window) {
	$scope.doSignup = function(doctor){
		$http({
        	method : "POST",
        	url : "/dosignup/",
        	headers: {
        			'Content-Type': 'application/x-www-form-urlencoded'
    			},
        	data:$.param({
        		title : doctor.title,
        		firstName: doctor.firstName,
        		lastName:doctor.lastName,
        		specialization:doctor.specialization,
        		addrLine1:doctor.line1,
        		addrLine2:doctor.line2,
        		postCode:doctor.postCode,
        		city:doctor.city,
        		mobile:doctor.mobile,
        		email:doctor.email,
        		website:doctor.website
        	})
    	}).then(function mySucces(response) {
        	$scope.myWelcome = response.data;
    	}, function myError(response) {
        	$scope.myWelcome = response.statusText;
    	});
	}
}])