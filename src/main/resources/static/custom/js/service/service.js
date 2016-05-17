$(window).load(function() {
	// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");
})

var module = angular.module('healthcareWebApp', [ 'ui.bootstrap' ]);
module.factory('hcService', [ '$timeout', '$http', function($timeout, $http) {
	return new Service($timeout, $http);
} ]);
var Service = (function() {
	function Service(timeout, http) {
		var _this = this;
		this.triggerValidation = function(ngModel, scope) {
			if (ngModel.$untouched) {
				ngModel.$setTouched();
				_this.timeout(function() {
					scope.$apply();
				});
			}
		};
		this.timeout = timeout;
		this.http = http;
	}

	Service.prototype.generateSelect2Box = function(element, params, scope,
			ngModel, path) {
		var validation = this.triggerValidation;
		$(element).select2(params);/*.on('select2:unselecting', function(e) {
			$(this).on('select2:opening', function(e) {
				e.preventDefault();
			});

		}).on('select2:unselect', function(e) {
			var sel = $(this);
			setTimeout(function() {
				sel.off('select2:opening');
			}, 1);
		}).on("select2-blur", function(elem) {
			 validation(ngModel, scope);
		}).on("select2-close", function(elem) {
			var select2Data = $(this).data("select2");
			// Manually blur search input on close to let placeholder reappear
			// See https://github.com/ivaynberg/select2/issues/1545
			if (select2Data) {
				select2Data.search.blur();
			}
			validation(ngModel, scope);
		});*/
		/*scope.$watchCollection(path, function() {
			var value = ngModel.$viewValue;
			//alert(value);
			$(element).select2('data', null);
			if (value !== undefined && value != null) {
				$(element).select2('val', value);
				ngModel.$viewValue = value;
				validation(ngModel, scope);
			}
		});*/
		
		
	};

	Service.prototype.getRemoteMultiPagedConfig = function(element, ngModel,
			placeholder, allowClear, url, pageSize, params) {

		return {
			placeholder : placeholder,
			multiple : true,
			// allowClear: allowClear,
			// dropdownAutoWidth: true,
			data : params,
			/*
			 * ajax: { quietMillis: 100, dataType: 'json', url: url, data:
			 * function (params) { return { queryStr: typeof params.term !=
			 * 'undefined' ? params.term:'', // search term page: params.page }; },
			 * processResults: function (data, params) { var results = [];
			 * $.each(data, function(index, item){ results.push({ id: item,
			 * text: item }); }); params.page = params.page || 1;
			 * 
			 * return { results: results, pagination: { more: (params.page * 30) <
			 * data.length } }; } },
			 */
			/*
			 * formatSelection: function (item) { if (typeof item === "string") {
			 * return item; }
			 * 
			 * return item; }, formatResult: function (item) {
			 * 
			 * if (typeof item === "string") { return item; } return item; },
			 * id: function (item) { if (typeof item === "string") { return
			 * item; } return item; },
			 */
			/*
			 * initSelection: function (elem, callback) { var value =
			 * 'hello';//ngModel.$viewValue;
			 * 
			 * if (!value) { callback(""); } //Some hpo term contain "," thats
			 * way ";" is used as a separator else if (typeof value === "string" &&
			 * value.indexOf(";") > -1) { callback(value.split(";")); } else if
			 * (typeof value === "string") { callback(value.split(",")); } else {
			 * callback(elem.value); } }
			 */

			initSelection : function(element, callback) {
				/*var preselected_ids = [];
		        if(elem.val())
		            $(elem.val().split(",")).each(function () {
		                preselected_ids.push({id: this});
		            });
		        
		        var pre_selections = [];
		        for(index in ngModel.$viewValue)
		            for(id_index in preselected_ids)
		                if (selections[index].id == preselected_ids[id_index].id)
		                    pre_selections.push(selections[index]);
		        
		        var preselections = pre_selections(preselected_ids);
		        console.log(preselections);
	            callback(preselections);*/
			}
			
			/*initSelection : function (element, callback) {
				  callback($(element).data(ngModel.$viewValue));
				}*/
		};
	};

	return Service;
})();
