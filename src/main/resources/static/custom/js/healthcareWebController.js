//module = angular.module('healthcareWebApp', ['ui.bootstrap']);
module.controller('HealthcareWebController', ['$http', '$scope', '$window', '$controller', function ($http, $scope, $window, $controller) {
		

	$controller('SignUpController',{
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


        $scope.initDoctorView = function (doctorId) {
            $http({
                method: "GET",
                url: "/rest/healthcare/doctor/" + doctorId,
                params: {}
            }).then(function succes(response) {
                $scope.doctor = response.data;
                $scope.doctorId = doctorId;
                if(typeof $scope.doctor.openingTime != 'undefined'){
                	$scope.openingTimes =  JSON.parse($scope.doctor.openingTime.time);
            	}
                
            }, function error(response) {
            });
        }

        $scope.addComment = function(comment, userId){
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
        
        $scope.openingTimeFrm = new Date();

        $scope.hstepFrm = 9;
        $scope.mstepFrm = 00;
        
        $scope.openingTimeTo = new Date();

        $scope.hstepTo = 9;
        $scope.mstepTo = 00;
        
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
            console.log('Time changed to: ' + $scope.openingTime);
          };


    }])
    
    

module.controller('HealthcareWebSearchController', ['$http', '$scope', '$window', '$controller', function ($http, $scope, $window, $controller) {
	
		var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $http.defaults.headers.common[header] = token;
	    console.log(token);
	    
	    $scope.getSearchResult = function(searchType, simpleSearchKey, specializations, cities, bigCurrentPage){
	    	if(searchType == 'simple'){
	    		$scope.getSimpleResult(simpleSearchKey, bigCurrentPage);
	    	}
	    	else if(searchType == 'advance'){
	    		$scope.getAdvanceSearchResult(specializations, cities, bigCurrentPage);
	    	}
	    };
    
	    $scope.getSimpleResult = function (item, bigCurrentPage) {
            $scope.simpleSearchItem = item;
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
            });
        };
        
        $scope.getAdvanceSearchResult = function (specializations, cities, bigCurrentPage){

        	 $scope.searchSpecialization = specializations;
         	 $scope.searchCity = cities;
         	
        	$http({
                method: "GET",
                url: "/rest/healthcare/advance-doctor-serch",
                params: {specializations:typeof specializations != 'undefined'?specializations.split(","):'',
                		cities: typeof cities != 'undefined' && cities !=''?cities.split(","):'',
              	  		page:bigCurrentPage}
            }).then(function mySucces(response) {
          	  $scope.simpleSearchResults = response.data;
	        }, function myError(response) {
	            
	        });
        };

        $scope.simpleResultPager = function (bigCurrentPage) {
            $scope.getSimpleResult($scope.simpleSearchItem, bigCurrentPage)
        };
        
        $scope.advanceSearch =function(page){
        	$scope.searchSpecialization = typeof $scope.searchSpecialization != 'undefined'?$scope.searchSpecialization:'none'
        	$scope.searchCity = typeof $scope.searchCity != 'undefined'?$scope.searchCity:null;
        	window.location.href = '/doctor-search-advance?specializations='+ $scope.searchSpecialization+'&cities='+$scope.searchCity+'&page='+page;
        };
       
     
    }])    