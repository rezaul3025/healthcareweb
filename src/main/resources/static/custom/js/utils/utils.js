hc={
		
		isPropertyExists: function (object, property) {
	        return (object.hasOwnProperty(property) && object[property] != "" && object[property] != null && object[property] != undefined);
	    },

	    getProperty: function (object, property, def) {
	        if (hc.isPropertyExists(object, property)) {
	            return object[property];
	        }
	        return def;
	    }
}

hcDirective ={
		convertAttributesToString: function (element, placeholder) {
	        var attributeString = "";
	        var attributes = element[0].attributes;
	        angular.forEach(attributes, function (attribute) {
	            if (attribute.name == "placeholder") {
	                placeholder = attribute.value;
	            }
	            else {
	                //Make sure attribute id is unique, by adding '_inputfield_' prefix
	                if (attribute.name != "id") {
	                    attributeString += attribute.name + "='" + attribute.value + "' ";
	                }
	                else {
	                    attributeString += attribute.name + "='_inputfield_" + attribute.value + "' ";
	                }
	            }
	        });
	        return attributeString + 'placeholder="' + placeholder + '" ';
	    },
		inputFieldTemplate: function (form, field, errorMessages, directive, placeholder, element) {
	        var attributeString = hcDirective.convertAttributesToString(element, placeholder);
	        // jump to parent scope for messages
	        var messageField = field.replace(/\.\$/g, '\.\$parent\.\$');
	        var offsetLeft = angular.element(element).prop('offsetLeft');
            var offsetTop = angular.element(element).prop('offsetTop')+48;
	        return '<div>' + '<div ng-class="fieldClass(' + form + '.' + field + ')">'
	            + '<input class="form-control" id="' + field + '" name="' + field + '"' + directive + ' ' + attributeString + '/>' + '</div>'
	            + '<div class="metro" ng-messages="' + form + '.' + messageField + '.$error" multiple=""' + 'ng-messages-include="' + errorMessages + '" ' + 'sh-scope-init="field=' + form + '.' + messageField + '"' + 'ng-show="fieldInteracted(' + form + '.' + messageField + ')"'+ '"></div></div>' + '</div>';
	    },
	    inputAreaFieldTemplate: function (form, field, errorMessages, directive, placeholder, element) {
	        var attributeString = hcDirective.convertAttributesToString(element, placeholder);
	        // jump to parent scope for messages
	        var messageField = field.replace(/\.\$/g, '\.\$parent\.\$');
	        var offsetLeft = angular.element(element).prop('offsetLeft');
            var offsetTop = angular.element(element).prop('offsetTop')+48;
	        return '<div>' + '<div ng-class="fieldClass(' + form + '.' + field + ')">'
	            + '<textarea class="form-control" id="' + field + '" name="' + field + '"' + directive + ' ' + attributeString + '></textarea>' + '</div>'
	            + '<div class="metro" ng-messages="' + form + '.' + messageField + '.$error" multiple=""' + 'ng-messages-include="' + errorMessages + '" ' + 'sh-scope-init="field=' + form + '.' + messageField + '"' + 'ng-show="fieldInteracted(' + form + '.' + messageField + ')"'+ '"></div></div>' + '</div>';
	    },
	    itemThumTemplate : function(form, field, errorMessages, title, description, imageurl, element){
	        var html ='<div class="col-sm-3 col-md-4">';
	        html +='<div class="thumbnail itemthum">';
	        html +='<img src="'+imageurl+'" alt="'+title+'"/>';
	        html +='<div class="caption">';
	        html +=' <h3>'+title+'</h3>';
	        html +='<p>'+description+'</p>';
	        html +='<p> <a href="#" class="btn btn-default" role="button">View details ..</a></p>';
	        html +='</div></div></div>';
	       
	        return html;
	     },
		selectFieldTemplate: function (form, field, errorMessages, directive, placeholder, element) {
	        var attributeString = shDirective.convertAttributesToString(element, placeholder);
	        // jump to parent scope for messages
	        var messageField = field.replace(/\.\$/g, '\.\$parent\.\$');
	        return '<div>' + '<div ng-class="fieldClass(' + form + '.' + field + ')">'
	            + '<select class="form-control select" id="' + field + '" name="' + field + '"' + directive + ' ' + attributeString + '/></select>' + '</div>'
	            + '<div class="metro" ng-messages="' + form + '.' + messageField + '.$error" multiple=""' + 'ng-messages-include="' + errorMessages + '" ' + 'sh-scope-init="field=' + form + '.' + messageField + '"' + 'ng-show="fieldInteracted(' + form + '.' + messageField + ')"'+ '"></div></div>' + '</div>';
	    },
	    selectBox:function(params, elememt, url){
	        $(elememt).select2({
	            ajax: {
	              url: url,
	              dataType: 'json',
	              delay: 250,
	              data: function (params) {
	                    var result = {
	                        query: params.term,
	                        page: params.page,
	                        pageSize: 10
	                    };
	                   // $.extend(result, params);
	                    return result;
	                },
	              processResults: function (data, params) {
	                // parse the results into the format expected by Select2
	                // since we are using custom formatting functions we do not need to
	                // alter the remote JSON data, except to indicate that infinite
	                // scrolling can be used
	                params.page = params.page || 1;
	           
	                return {
	                  results: data,
	                  pagination: {
	                    more: (params.page * 30) < data.length
	                  }
	                };
	              },
	              cache: true
	            },
	            escapeMarkup: function (markup) 
	            { 
	            	return markup; 
	            
	            }, // let our custom formatter work
	            minimumInputLength: 1,
	            templateResult: function (item) {
	                return item.title;
	            },
	            templateSelection: function (item) {
	                return item;
	            },
	            id: function (item) {
	                return item;
	            }//,
	            //templateResult: formatRepo, // omitted for brevity, see the source of this page
	            //templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
             });
	    	
	    	/* $(elememt).select2(  {placeholder: "test",
	    	            dropdownAutoWidth: true,
	    	            allowClear: true,
	    	            quietMillis: 100,
	    	            ajax: {
	    	                url: url,
	    	                data: function (term, page) {
	    	                    var result = {
	    	                    	query: param.term,
	    	                        page: param.page,
	    	                        pageSize: 0
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
	    	                return item;
	    	            },
	    	            formatResult: function (item) {
	    	                return item;
	    	            },
	    	            id: function (item) {
	    	                return item;
	    	            },
	    	            initSelection: function (element, callback) {
	    	                callback($(element).val());
	    	            }}).on("select2-blur", function (elem) {
	             //validation(ngModel, scope);
	         }).on("select2-close", function (elem) {
	             var select2Data = $(this).data("select2");
	             // Manually blur search input on close to let placeholder reappear
	             // See https://github.com/ivaynberg/select2/issues/1545
	             if (select2Data) {
	                 select2Data.search.blur();
	             }
	             validation(ngModel, scope);
	         });
	    	*/
	    }
}

module.controller('CoreController', ['$scope', '$http', function ($scope, $http) {
   
    $scope.submit = function (form) {
        $scope.triggerValidation(form);
        if (form.$valid) {
            $scope.submitAction(form);
        }
    };
    $scope.submitAction = function (form) {
    };
    var triggerValidation = function (element) {
        if (element.hasOwnProperty("$submitted")) {
            angular.forEach(element, function (value, key) {
                if (key.charAt(0) != '$') {
                    triggerValidation(value);
                }
            });
        }
        else {
            element.$setTouched();
        }
    };
    $scope.triggerValidation = function (form) {
        triggerValidation(form);
    };
    $scope.fieldClass = function (field) {
        return { 'error-state': $scope.fieldInvalid(field), 'success-state': $scope.fieldValid(field) };
    };
    $scope.fieldInvalid = function (field) {
        if (field === undefined) {
            return false;
        }
        return $scope.fieldInteracted(field) && field.$invalid;
    };
    $scope.fieldValid = function (field) {
        if (field === undefined) {
            return true;
        }
        return $scope.fieldInteracted(field) && field.$valid;
    };
    $scope.fieldInteracted = function (field) {
        if (field === undefined) {
            return false;
        }
        return $scope.submitted || field.$touched;
    };
    $scope.fieldError = function (field) {
        if (field === undefined) {
            return false;
        }
        return field.$error;
    };
    
    /*$scope.isRequired = function(value){
    	if(typeof value == 'undefined' || value == null){
    		return false;
    	}
    	
    	return value;
    };*/
}]);

module.directive('hcScopeInit', function () {
    return {
        scope: true,
        priority: 1,
        compile: function () {
            return {
                pre: function (scope, element, attrs) {
                    scope.$eval(attrs.cgScopeInit);
                }
            };
        }
    };
});