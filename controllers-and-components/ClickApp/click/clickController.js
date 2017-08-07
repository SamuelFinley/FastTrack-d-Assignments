angular.module ('clickModule')
.controller ('clickController', ['clickService', function (clickies) {

  this.clickService = clickies
  //this.clickConfig = clickies

  this.values =
    {
      'multiplier': 1,
      'total': 0,
      'autoclicker': 0,
      'multshow': 1.2,
      'buttoncolorA': {'background-color': 'GREY'},
      'buttoncolorB': {'background-color': 'GREY'},
      'disableA': false,
      'disableB': false
    }
}])
