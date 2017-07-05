'use strict'

// returns an array from start (inclusive) to end (exclusive)
const range =
  (start, end) => {
    return Array.apply(null, Array(end - start + 1)).map(function (_, i) { return i + 1 })
  }

const config = [
  [3, 'Fizz'],
  [5, 'Buzz']
]

const fizzbuzz =
  (range, config) => {
    console.log(range.map(function (_, i) {
    if ((range[i] % config[0][0]) === 0 && (range[i] % config[1][0]) === 0) {
      return `${i + 1}: ${config[0][1]}${config[1][1]}`
    } else if (range[i] % config[1][0] === 0) {
      return `${i + 1}: ${config[1][1]}`
    } else if (range[i] % config[0][0] === 0) {
      return `${i + 1}: ${config[0][1]}`
    } }))
  }

fizzbuzz(range(1, 115), config)
