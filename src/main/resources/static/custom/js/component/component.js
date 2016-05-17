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
            return '<input type="text" name="' + attrs.name + '" hc-combo="" id="' + attrs.id + '" ></input>';

        }

    }
});

module.directive('hcCombo', ['hcService', '$http', function (hcService, $http) {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, element, attrs, ngModel) {

                /*results.push({
                 id: 'itme',
                 text: 'item'
                 });*/
                $http.get(attrs.url, {
                    params: {queryStr: 'y'}
                }).then(function (response) {

                    var results = [];
                    $.each(response.data, function (index, item) {
                        results.push({
                        	id: typeof item == 'object'?item.name:item,
                            text: typeof item == 'object'?item.name:item
                        });
                    });

                    var selectParams = hcService.getRemoteMultiPagedConfig(element, ngModel, 'Select sp1 terms', true, "/rest/healthcare/specializations", 5, results);

                    hcService.generateSelect2Box(element, selectParams, scope, ngModel, attrs.ngModel);
                });


                /* ngModel.$validators.mandatory = cgValidationService.generateMandatoryValidator(); 
                 
                 ngModel.$asyncValidators.existing = cgValidationService.generateAsyncValidator('rest/hpoterm/exists');*/
            }
        };
    }]);

module.directive('hcFileUploadField', [function () {
        return {
            restrict: 'E',
            replace: true,
            template: function (element, attrs) {
                var form = hc.getProperty(attrs, "hcForm", "fileUploadForm");
                var field = hc.getProperty(attrs, "hcField", attrs.id);
                var errors = hc.getProperty(attrs, "hcErrorMessages", "logoUploadMessages");
                return hcDirective.inputFieldTemplate(form, field, errors, "hc-file-upload=''", "Select file", element);
            }
        };
    }]);

module.directive('hcFileUpload', ['hcUtilsService', '$parse', function (hcUtilsService, $parse) {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, element, attrs, ngModel) {

                /*
                 * ngModel.$render = function () {
                 * ngModel.$setViewValue(element.val()); };
                 */
                /*el.bind('change', function (event) {
                 var files = event.target.files;
                 //iterate files since 'multiple' may be specified on the element
                 for (var i = 0;i<files.length;i++) {
                 //emit event upward
                 scope.$emit("fileSelected", { file: files[i] });
                 }                                       
                 });*/


                element.bind('change', function () {
                    /*
                     * scope.$apply(function () {
                     * ngModel.$render();
                     * });
                     */
                    var values = [];
                    angular.forEach(element[0].files, function (item) {
                        /*var value1 = {
                         // File Name 
                         name: item.name,
                         //File Size 
                         size: item.size,
                         //File URL to view 
                         url: URL.createObjectURL(item),
                         // File Input Value 
                         _file: item
                         };*/
                        values.push(item);
                    });

                    var model = $parse(attrs.ngModel);
                    var modelSetter = model.assign;

                    scope.$apply(function () {
                        //for (var i = 0; i < element[0].files.length; ++i) {
                        modelSetter(scope, values);
                        //}
                    });

                });

                ngModel.$validators.validFileCheck = function (value) {
                    if (typeof value == 'undefined') {
                        return true;
                    } else if (!hcUtilsService.isValidFileFormat(attrs.formats, value[0].name)) {
                        return false;
                    } else {
                        return true;
                    }
                };

                ngModel.$validators.validFileNameCheck = function (value) {
                    if (typeof value == 'undefined') {
                        return true;
                    } else if (value[0].name.split(".").length > 2) {
                        return false;
                    } else {
                        return true;
                    }
                };

                ngModel.$validators.fileSizeCheck = function (value) {
                    var maxDocSize = parseInt(attrs.docmaxsize);
                    if (typeof value == 'undefined') {
                        return true;
                    } else if (Math.round(value[0].size / 1024) > maxDocSize) {
                        return false;
                    } else {
                        return true;
                    }
                };
            }
        };
    }]);

module.directive('ngSparkline', function () {
    return {
        restrict: 'E',
        template: '<div class="sparkline">fgfgfgfg</div>'
    }
});
