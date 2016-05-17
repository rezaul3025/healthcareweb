
module.controller('SignUpController', ['$http', '$scope', '$window','$controller', function ($http, $scope, $window, $controller) {
   
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $http.defaults.headers.common[header] = token;
            console.log(token);
      
        
        $scope.specializations = [];
        $scope.getAllspecializations = function () {
            $http({
                method: "GET",
                url: "/specializations/",
                params: {queryStr: 'bio'}
            }).then(function succes(response) {
                // $scope.specializations = response.data;
                // alert($scope.specializations)
            }, function error(response) {
                $scope.error = response.statusText;
            });
        }



        $scope.doSignup = function (doctor, option) {
            
            console.log(doctor.specializations);
            doctor['specializations'] = typeof doctor.specializations == 'string' ? doctor.specializations.split(',') : doctor.specializations;
            var user = {
                email: doctor.emailPhone,
                password: doctor.password,
                role: 'DOCTOR',
                firstName: doctor.firstName,
                lastName: doctor.lastName
            }

            doctor['user'] = user;
            
            doctor['openingTime'] =  {'time':JSON.stringify($scope.openingTimes)};

            $http({
                method: "POST",
                url: "/rest/healthcare/doctor-signup",
                data: doctor,
            }).then(function mySucces(response) {
                $scope.doctor = response.data;
                
                if(typeof doctor.imageUpload != 'undefined'){
	                var imgUpUrl = '/rest/healthcare/doctor-img-upload/'+doctor.id;
	                var formData = new FormData();
	       	  	 	for(var i = 0;i<doctor.imageUpload.length;i++){
	       	  	 		formData.append('doctor_image'+i, doctor.imageUpload[i]);
	       	  	 	}
		       	  	 	
		       	  	$http({
		                method: 'POST',
		                url: imgUpUrl,
		                data: formData,
		                transformRequest: angular.identity,
		                headers: {'Content-Type': undefined, 'Content-Transfer-Encoding': 'utf-8'}
		            }).
		            success(function(data, status, headers, config) {
		           	
		            })
		            .error(function(data, status, headers, config) {
		                
		            })
                }
                
                if(option == 'u'){
                	window.location.href = '/doctor-edit/'+doctor.id;
                }
	                
	        }, function myError(response) {
	             $scope.myWelcome = response.statusText;
	        });
        }
        
        $scope.doUserSignup = function (user) {
            console.log(user);
            user['role'] = 'USER';
            user['doctorId'] = null;
            $http({
                method: "POST",
                url: "/rest/healthcare/user-signup",
                data: user,
            }).then(function mySucces(response) {
                $scope.myWelcome = response.data;
            }, function myError(response) {
                $scope.myWelcome = response.statusText;
            });
        }
    }])