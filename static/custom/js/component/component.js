//var module = angular.module('healthcareWebApp', ['ui.bootstrap']);

module.directive('hcComboDiv', function () {
    return {
        restrict: 'E',
        replace: true,
        template: function (element, attrs) {
        
            //var form = cg.getPropertyOrDefault(attrs, "cgForm", "ciForm");
           // var field = cg.getPropertyOrDefault(attrs, "cgField", hpo);
            //var errors = cg.getPropertyOrDefault(attrs, "cgErrorMessages", "clinicalInfoHpoMessages");
            //return cgDirectives.inputTemplateSelect(form, field, errors, errors, "cg-hpo=''", "Select hpo terms", element);
        	return '<input type="text" name="'+attrs.name+'" hc-combo="" id="'+attrs.id+'" ></input>';
        	
        }
    	
    }
});

module.directive('hcCombo',['hcService','$http', function (hcService, $http) {
	return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
        	
        	/*results.push({
	              id: 'itme',
	              text: 'item'
	            });*/
        	$http.get(attrs.url, {
         	      params: {	queryStr:'y' }
         	    }).then(function(response){
         	    	
         	    	 var results = [];
   		          $.each(response.data, function(index, item){
   		            results.push({
   		              id: item,
   		              text: item
   		            });
   		          });
         	    	
         	    	 var selectParams = hcService.getRemoteMultiPagedConfig(element, ngModel, 'Select sp1 terms', true, "/specializations", 5, results);
                     
                     hcService.generateSelect2Box(element, selectParams, scope, ngModel, attrs.ngModel);
         	    });
           
            
           /* ngModel.$validators.mandatory = cgValidationService.generateMandatoryValidator(); 
           
            ngModel.$asyncValidators.existing = cgValidationService.generateAsyncValidator('rest/hpoterm/exists');*/
        }
    };
}]);

module.directive('ngSparkline', function() {
	  return {
	    restrict: 'E',
	    template: '<div class="sparkline">fgfgfgfg</div>'
	  }
	});
