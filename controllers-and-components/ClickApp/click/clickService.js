angular.module('clickModule')
.service('clickService', function($interval) {
  var time

  function conditsA (values) {
    if (values.total >= 10) {
      return {'background-color': 'WHITE'}
    }
    if (values.total < 10) {
      return {'background-color': 'GREY'}
    }
  }

  function conditsB (values) {
    if (values.total >= 100) {
      return {'background-color': 'WHITE'}
    }
    if (values.total < 100) {
      return {'background-color': 'GREY'}
    }
  }

  this.incrementTotal = (values) => {
    values.total += values.multiplier
    values.total = Math.round(values.total * 100) / 100
    values.buttoncolorA = conditsA(values)
    values.buttoncolorB = conditsB(values)
    console.log(values.buttoncolorA)
  }

  this.incrementMult = (values) => {
    if (values.total >= 10) {
      values.multiplier *= 1.2
      values.multshow *= 1.2
      values.total -= 10
      values.buttoncolorA = conditsA(values)
      values.buttoncolorB = conditsB(values)
    }
  }

  this.autoClicker = (values) => {
    if (values.total >= 100) {
      let mult = values.multiplier
      time = $interval(function () {
        values.total += mult
        values.total = Math.round(values.total * 100) / 100
      }, 1000)
    }
  }

  this.reset = (values) => {
    values.multiplier = 1
    values.total = 0
    values.autoclicker = 0
    values.multshow = 1.2
    $interval.cancel(time)
    window.localStorage.removeItem('total')
    window.localStorage.removeItem('mult')
    window.localStorage.removeItem('count')
  }
})
