'use strict'

const listing =
  (name, price) => ({
    name,
    price
  })

const cart =
  (customer, ...items) => ({
    customer,
    items
  })

const listedPrice =
  listing =>
    name =>
      name === listing.name
        ? listing.price
        : 0
/**
 * transform carts into an array of { customer, total }
 */
const calculateTotals =
  listings =>
    carts => {
      let ans = []
      carts.forEach(crt => {
        let totals = 0
        listings.forEach(list => {
          crt.items.forEach(ct => {
            if (listedPrice(list)(ct) > 0) {
              totals += listedPrice(list)(ct)
            }
          })
        })
        ans.push({customer: crt.customer, total: totals})
      })
      return ans
    }
module.exports = {
  listing,
  cart,
  calculateTotals
}
