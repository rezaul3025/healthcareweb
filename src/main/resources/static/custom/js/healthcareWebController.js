//module = angular.module('healthcareWebApp', ['ui.bootstrap']);
module.controller('HealthcareWebController', ['$http', '$scope', '$window', '$controller', function ($http, $scope, $window, $controller) {
		

	$controller('SignUpController',{
		$scope:$scope
	});
	$controller('HealthcareWebSearchController',{
		$scope:$scope
	});
		
		var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $http.defaults.headers.common[header] = token;
	    console.log(token);
    
        $scope.simplesearchViewTh = true;
        $scope.simplesearchViewThList = false;

        $scope.changeSimpleSearchView = function () {
            if ($scope.simplesearchViewTh) {
                $scope.simplesearchViewTh = false;
            } else {
                $scope.simplesearchViewTh = true;
            }

            if ($scope.simplesearchViewThList) {
                $scope.simplesearchViewThList = false;
            } else {
                $scope.simplesearchViewThList = true;
            }
        }

        $scope.getSearchKey = function (val) {
            return $http.get('/rest/healthcare/search-autocomplete', {
                params: {
                    queryStr: val
                }
            }).then(function (response) {
                return response.data;
            });
        };

        $scope.doSimpleSearch = function (item, model, label, event, bigCurrentPage) {
            /*$scope.simpleSearchItem = item;
            $http({
                method: "GET",
                url: "/rest/healthcare/simple-doctor-serch",
                params: {term: item,
                    page: bigCurrentPage - 1,
                    pageSize: 10}
            }).then(function succes(response) {
                $scope.simpleSearchResults = response.data;
                // $scope.bigTotalItems = response.data.length;
                $scope.bigCurrentPage = bigCurrentPage;

                $http({
                    method: "GET",
                    url: "/rest/healthcare/simple-doctor-serch-count",
                    params: {term: item}
                }).then(function succes(response) {
                    $scope.bigTotalItems = response.data;
                }, function error(response) {
                });

            }, function error(response) {
            });*/
        	
        	window.location.href = '/doctor-search-simple?key='+item+'&page='+bigCurrentPage;
        };

        /*$scope.simpleResultPager = function (bigCurrentPage) {
            $scope.getSimpleResult($scope.simpleSearchItem, null, null, null, bigCurrentPage)
        };*/

        $scope.ratingPointsArr = [{"point": 1, "isActive": false},
            {"point": 2, "isActive": false},
            {"point": 3, "isActive": false},
            {"point": 4, "isActive": false},
            {"point": 5, "isActive": false}]

        $scope.ratingPoints = 0;
        $scope.ratingPointchnage = function (value, point, search) {
           
            $scope.ratingPoints = point;
            if (!value) {
                for (var r = $scope.ratingPoints; r < $scope.ratingPointsArr.length; r++) {
                    $scope.ratingPointsArr[r].isActive = value;
                }
            } else {
                for (var r = 0; r < $scope.ratingPoints; r++) {
                    $scope.ratingPointsArr[r].isActive = value;
                }

               // alert(search.ratingPoints);
                //window.location.href = '/advancesearch?specializationsStr=' + search.specialization + '&cityStr=' + search.city + '&ratingPoints=' + search.ratingPoints + '&page=1';
            }
        }

        $scope.search = function (search, ratingPoints) {

            //search['ratingPoints'] = ratingPoints;
            //alert(search.ratingPoints);
            // $window.location.href = '/advancesearch?specializationsStr='+search.specialization+'&cityStr='+search.city+'&ratingPoints='+search.ratingPoints+'&page=1';
        };
        $scope.search.specialization = 'bio';
        $scope.getAdvanceSearchParams = function (search) {
            $http.get('/getadvancedsearchparams/', {
                params: {}
            }).then(function (response) {
                search.specialization = response.data.specializations;
                search.city = response.data.cities;
                search.ratingPoints = response.data.ratingPoints;
                //$scope.ratingPointchnage(true,search.ratingPoints, search);
                $scope.ratingPoints = response.data.ratingPoints;
            });

            //$scope.ratingPointchnage(true,search.ratingPoints, search);
        }

        $scope.currentPage = 1;
        $scope.maxSize = 5;


        $scope.initDoctorView = function (doctorId, isAuthenticated) {
        	$scope.isAuthenticated = isAuthenticated;
            $http({
                method: "GET",
                url: "/rest/healthcare/doctor/" + doctorId,
                params: {}
            }).then(function succes(response) {
                $scope.doctor = response.data;
                $scope.doctorId = doctorId;
                if(typeof $scope.doctor.openingTime != 'undefined'){
                	$scope.openingTimes =  JSON.parse($scope.doctor.openingTime.time);
                	$scope.openingTimes = $scope.openingTimes == null ? []:$scope.openingTimes;
            	}
                
            }, function error(response) {
            });
        }

        $scope.addComment = function(comment, userId){
        	if(typeof $scope.isAuthenticated != 'undefined' && $scope.isAuthenticated){
	        	comment['rate'] = $scope.ratingPoints;
	        	comment['doctorId'] = $scope.doctorId; 
	        	comment['userId'] = userId;
	        	
	        	$http({
	                method: "POST",
	                url: "/rest/healthcare/comment-on-doctor",
	                data: comment,
	            }).then(function mySucces(response) {
	            	 $scope.initDoctorView($scope.doctorId);
	            }, function myError(response) {
	                $scope.myWelcome = response.statusText;
	            });
        	}
        	else{
        		window.location.href = 'healthcare/login';
        	}
        }
        
        $scope.openingTimeFrm = new Date();

        $scope.hstepFrm = 9;
        $scope.mstepFrm = 1;
        $scope.ismeridianFrm = false;
        
        $scope.openingTimeTo = new Date();

        $scope.hstepTo = 9;
        $scope.mstepTo = 1;
        
        $scope.openingTimes = [];
        
        $scope.setTime = function(fromDay, toDay, fromDate, toDate){
        	if(typeof toDay != 'undefined'){
        		fromDay = fromDay+'-'+toDay;
        	}
        	var time = [];
        	var fromTime = fromDate.getHours()+":"+fromDate.getMinutes();
        	var toTime = toDate.getHours()+":"+toDate.getMinutes();
        	var timeEntry = {'from':fromTime,'to':toTime};
        	time.push(timeEntry);
        	var entry = {'day':fromDay,'time':time};
        	if($scope.openingTimes.length == 0){
        		$scope.openingTimes.push(entry);
        	}
        	else if(!isEntryExist(fromDay)){
        		
        		$scope.openingTimes.push(entry);
        	}
        	else{
        		for(var e in $scope.openingTimes){
        			var fDay = $scope.openingTimes[e].day;
        			if(fDay == fromDay){
        				$scope.openingTimes[e].time.push(timeEntry);
        			}
        		}
        	}
        	
        }
        
        $scope.removeTime = function(index){
        	$scope.openingTimes.splice(index,1);
        }
        
        isEntryExist = function(entry){
        	for(var e in $scope.openingTimes){
        		if(entry == $scope.openingTimes[e].day){
        			return true;
        		}
        	}
        	
        	return false;
        }
        
        $scope.changed = function () {
            console.log('Time changed to: ' + $scope.openingTimeTo);
          };


    }])
    
    

module.controller('HealthcareWebSearchController', ['$http', '$scope', '$window', '$controller', function ($http, $scope, $window, $controller) {
	
		var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $http.defaults.headers.common[header] = token;
	    console.log(token);
	    
	    $scope.getSearchResult = function(searchType, simpleSearchKey, specializations, cities, rate, bigCurrentPage){
	    	if(searchType == 'simple'){
	    		$scope.getSimpleResult(simpleSearchKey, bigCurrentPage);
	    	}
	    	else if(searchType == 'advance' && ((specializations != null && specializations != '') || (cities != null && cities != ''))){
	    		$scope.getAdvanceSearchResult(specializations, cities, rate, bigCurrentPage);
	    	}
	    	else if(searchType == 'advance' && ((specializations == null || specializations == '') && (cities == null || cities == ''))){
	    		$scope.getSimpleResult(simpleSearchKey, bigCurrentPage);
	    	}
	    	
	    	console.log(" Advance search params : "+specializations+" , "+cities);
	    	console.log("Simaple search params : "+simpleSearchKey+", page: "+bigCurrentPage);
	    };
    
	    $scope.getSimpleResult = function (item, bigCurrentPage) {
            $scope.simpleSearchItem = item;
            $http({
                method: "GET",
                url: "/rest/healthcare/simple-doctor-serch",
                params: {term: item,
                    page: bigCurrentPage,
                    pageSize: 10}
            }).then(function succes(response) {
                $scope.simpleSearchResults = response.data;
                // $scope.bigTotalItems = response.data.length;
                $scope.bigCurrentPage = bigCurrentPage;

                $http({
                    method: "GET",
                    url: "/rest/healthcare/simple-doctor-serch-count",
                    params: {term: item}
                }).then(function succes(response) {
                    $scope.bigTotalItems = response.data;
                }, function error(response) {
                });

            }, function error(response) {
            });
        };
        
        $scope.simpleResultPager = function (bigCurrentPage) {
            $scope.getSimpleResult($scope.simpleSearchItem, bigCurrentPage)
        };
        
        $scope.getAdvanceSearchResult = function (specializations, cities, rate, bigCurrentPage){

        	 $scope.searchSpecialization = specializations;
         	 $scope.searchCity = cities;
         	$scope.rate = rate;
         	
        	$http({
                method: "GET",
                url: "/rest/healthcare/advance-doctor-serch",
                params: {specializations:typeof specializations != 'undefined' && specializations != ''?specializations.split(","):'',
                		cities: typeof cities != 'undefined' && cities !=''?cities.split(","):'',
                		rate: rate,
              	  		page:bigCurrentPage}
            }).then(function mySucces(response) {
          	  $scope.simpleSearchResults = response.data;
	        }, function myError(response) {
	            
	        });
        };

       
        
        $scope.advanceSearch =function(page, rate){
        	$scope.searchSpecialization = typeof $scope.searchSpecialization != 'undefined' && $scope.searchSpecialization != ''?$scope.searchSpecialization:''
        	$scope.searchCity = typeof $scope.searchCity != 'undefined' && $scope.searchCity !=''?$scope.searchCity:'';
        	$scope.rate = rate;
        	window.location.href = '/doctor-search-advance?specializations='+ $scope.searchSpecialization+'&cities='+$scope.searchCity+'&rate='+$scope.rate+'&page='+page;
        };
        
        
        
        $scope.calculateAvgRate = function(comments){
        	var totalRate = 0;
        	for(comment in comments){
        		totalRate +=parseInt(comments[comment].rate);
        	}
        	
        	if(totalRate == 0){
        		return 0;
        	}
        	
        	$scope.remainTopRate = new Array(5-Math.round(totalRate/comments.length))
        	
        	return new Array(Math.round(totalRate/comments.length));   
        }
       
     
    }])    