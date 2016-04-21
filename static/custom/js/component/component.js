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
        	return '<input type="text" name="specializations" hc-combo="" id="specializations" />';
        	
        }
    	
    }
});

module.directive('hcCombo',['hcService', function (hcService) {
	return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            var selectParams = hcService.getRemoteMultiPagedConfig(element, ngModel, 'Select sp1 terms', true, "/specializations", 10, {});
            
            hcService.generateSelect2Box(element, selectParams, scope, ngModel, attrs.ngModel);
            
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
