'use strict'

const entries =
  obj =>
    Object.keys(obj)
      .map(key => [key, obj[key]])

const listing =
  (name, price) => ({
    name,
    price
  })

const customer =
  (name, shoppingList) => ({
    name,
    shoppingList
  })

const cart =
  (customer, ...items) => ({
    customer,
    items
  })

/**
 * should return an array with the `itemName` repeated `count` number of times
 */
const itemRepeater =
  itemName =>
    count => {
      return Array.apply(null, Array(count)).map(element => itemName)
    }

/**
 * should return an array of carts with each given customer's shopping list
 * as an array of items
 */
const constructCarts =
  listings =>
    customers => {
      return customers.map(peeps => {
        let items = []
        entries(peeps.shoppingList).forEach(item => {
          items = [...items, ...itemRepeater(item[0])(item[1])]
        })
        return cart(peeps.name, ...items)
      })
    }
module.exports = {
  listing,
  customer,
  constructCarts
}
