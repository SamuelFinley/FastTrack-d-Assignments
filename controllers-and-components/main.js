
$(document).ready(function () {
  //
  let total = 0
  let count = 0
  let mult = 1
  //
  function condits () {
    if (total >= 100) {
      $('#buttonSequel').css('background-color', 'WHITE')
    }
    if (total >= 10) {
      $('#rebutton').css('background-color', 'WHITE')
    }
    if (total < 10) {
      $('#rebutton').css('background-color', 'GREY')
    }
    if (total < 100) {
      $('#buttonSequel').css('background-color', 'GREY')
    }
  }
//
  function timerThing () {
    total += mult
    condits()
    $('#total').html('Total: ' + Math.round(total * 100) / 100)
  }
//
  if (window.localStorage.total) {
    total = Number(window.localStorage.total)
    condits()
    $('#total').html('Total: ' + Math.round(total * 100) / 100)
  }
  if (window.localStorage.mult) {
    mult = Number(window.localStorage.mult)
    if (mult === 1) {
      $('#rebutton').html('*1.2')
      $('#buttoning').html('+1')
    } else {
      $('#rebutton').html('*' + Math.round(mult * 100) / 100)
      $('#buttoning').html('+' + Math.round(mult * 100) / 100)
    }
  }
  if (window.localStorage.count) {
    count = Number(window.localStorage.count)
    for (let i = 0; i < count; i++) {
      var time = window.setInterval(timerThing, 1000)
    }
    $('#count').html('<br>Autoclickers: <br>' + count + '<br>')
  }
  window.setInterval(function autosave () {
    window.localStorage.setItem('total', total)
    window.localStorage.setItem('mult', mult)
    window.localStorage.setItem('count', count)
  }, 1000)
    $('#buttoning').click(() => {
      total += mult
      condits()
      $('#total').html('Total: ' + Math.round(total * 100) / 100)
    })
//
    $('#rebutton').click(() => {
      if (total >= 10) {
        total -= 10
        condits()
        mult = mult * 1.2
        $('#rebutton').html('*' + Math.round(mult * 100) / 100)
        $('#buttoning').html('+' + Math.round(mult * 100) / 100)
        $('#total').html('Total: ' + Math.round(total * 100) / 100)
      }
    })

    $('#buttonSequel').click(() => {
      if (total >= 100) {
        var time = window.setInterval(timerThing, 1000)
        total -= 100
        count++
        $('#count').html('<br>Autoclickers: <br>' + count + '<br>')
      }
    })
  //
    $('#clear').click(() => {
      total = 0
      count = 0
      mult = 1
      window.clearInterval(time)
      window.clearInterval(time)
      window.localStorage.removeItem('total')
      window.localStorage.removeItem('mult')
      window.localStorage.removeItem('count')
      $('#rebutton').html('*1.2')
      $('#buttoning').html('+1')
      $('#total').html('Total: ' + Math.round(total * 100) / 100)
      $('#count').html('<br>Autoclickers: <br>' + count + '<br>')
    })
})
