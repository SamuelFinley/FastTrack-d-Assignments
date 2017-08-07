angular.module('clickModule')
.component('headerElement', {
  templateUrl: 'ClickApp/click/resetElementTemplate.html',
  bindings: {
    'reset': '=',
    'values': '='
  }
})
