angular.module('clickModule')
.component('bodyElement', {
  templateUrl: 'ClickApp/click/clickElementTemplate.html',
  bindings: {
    'incrementTotal': '=',
    'incrementMult': '=',
    'autoClicker': '=',
    'values': '='
  }
})
