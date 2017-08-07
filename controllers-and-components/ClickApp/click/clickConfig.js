 angular.module ('clickModule')
 .config (function () {
    save = $interval(function () {
      window.localStorage.setItem('total', values.total)
      window.localStorage.setItem('multiply', values.multiply)
      window.localStorage.setItem('multshow', values.multshow)
      window.localStorage.setItem('autoclicker', values.autoclicker)
   }, 1000)
 })
