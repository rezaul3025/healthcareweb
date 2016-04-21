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
            //validation(ngModel, scope);
        }).on("select2-close", function (elem) {
            var select2Data = $(this).data("select2");
            // Manually blur search input on close to let placeholder reappear
            // See https://github.com/ivaynberg/select2/issues/1545
            if (select2Data) {
                select2Data.search.blur();
            }
            //validation(ngModel, scope);
        });
        scope.$watchCollection(path, function () {
            var value = ngModel.$viewValue;
            $(element).select2('data', null);
            if (value !== undefined && value != null) {
               
                $(element).select2('val', value);
               // validation(ngModel, scope);
            }
        });
    };
    
   
    Service.prototype.getRemoteMultiPagedConfig = function (element, ngModel, placeholder, allowClear, url, pageSize, params) {
        return {
            placeholder: placeholder,
            multiple: true,
            allowClear: allowClear,
            dropdownAutoWidth: true,
            quietMillis: 100,
            ajax: {
                url: url,
                data: function (term, page) {
                    var result = {
                        selected: element.val(),
                        query: term,
                        page: page,
                        pageSize: pageSize
                    };
                    $.extend(result, params);
                    return result;
                },
                results: function (data, page) {
                    var more = (page * pageSize) < data.count;
                    return { results: data.results, more: more };
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
                var value = ngModel.$viewValue;
              
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
