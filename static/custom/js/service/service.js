var module = angular.module('healthcareWebApp',['ui.bootstrap']);

module.factory('hcService', ['$timeout', function ($timeout) {
    return new Service($timeout);
}]);
var Service = (function () {
    function Service(timeout, utilsService) {
        var _this = this;
        this.triggerValidation = function (ngModel, scope) {
            if (ngModel.$untouched) {
                ngModel.$setTouched();
                _this.timeout(function () {
                    scope.$apply();
                });
            }
        };
        this.timeout = timeout;
    }
    
    Service.prototype.generateSelect2Box = function (element, params, scope, ngModel, path) {
        var validation = this.triggerValidation;
        $(element).select2(params).on("select2-blur", function (elem) {
            validation(ngModel, scope);
        }).on("select2-close", function (elem) {
            var select2Data = $(this).data("select2");
            // Manually blur search input on close to let placeholder reappear
            // See https://github.com/ivaynberg/select2/issues/1545
            if (select2Data) {
                select2Data.search.blur();
            }
            validation(ngModel, scope);
        });
        /*scope.$watchCollection(path, function () {
            var value = ngModel.$viewValue;
            $(element).select2('data', null);
            if (value !== undefined && value != null) {
               
                $(element).select2('val', value);
               validation(ngModel, scope);
            }
        });*/
    };
    
   
    Service.prototype.getRemoteMultiPagedConfig = function (element, ngModel, placeholder, allowClear, url, pageSize) {
        return {
        	placeholder: placeholder,
            multiple: true,
            allowClear: allowClear,
            dropdownAutoWidth: true,
            ajax: {
            	quietMillis: 100,
                dataType: 'json',
                url: url,
                data: function (params) {
 		           return {
 				        queryStr: typeof params.term != 'undefined' ? params.term:'', // search term
 				        page: params.page
 				      };
 		        },
 		        processResults: function (data,  params) {
 		          var results = [];
 		          $.each(data, function(index, item){
 		            results.push({
 		              id: item,
 		              text: item
 		            });
 		          });
 		          params.page = params.page || 1;
 		
 		          return {
 		              results: results,
 				        pagination: {
 				          more: (params.page * 30) < data.length
 				        }
 		          };
 		        }
            },
            formatSelection: function (item) {
                if (typeof item === "string") {
                    return item;
                }
               
                return item;
            },
            formatResult: function (item) {
            	
                if (typeof item === "string") {
                    return item;
                }
                return item;
            },
            id: function (item) {
                if (typeof item === "string") {
                    return item;
                }
                return item;
            },
            initSelection: function (elem, callback) {
                var value = 'hello';//ngModel.$viewValue;
              
                if (!value) {
                    callback("");
                }
              //Some hpo term contain ","  thats way ";" is used as a separator
                else if (typeof value === "string" && value.indexOf(";") > -1) {
                    callback(value.split(";"));
                }
                else if (typeof value === "string") {
                    callback(value.split(","));
                }
                else {
                    callback(elem.value);
                }
            }
        };
    };
    
    return Service;
})();
