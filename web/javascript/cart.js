(function() {
// VARS
const remove = document.querySelector("remove");


 // FUNCTIONS

 //Get cart for user
 function getcartContent() {
    // get contents from database.
    // if nothing is there, create an empty array
    const cartContent = ;
    return cartContent;
  }

//Save cart for user
function setcartContent(lsContent) {
    // save content inside local storage
    
  }

//Calculate total for total price section (doesn't include tax right now)
  function calculateTotal(prices) {
    // calculate the total price in the cart
  }

  function getCartItemPrices() {
    // extract the price numbers from the cart items to calculate total
    const prices = [];
    // retrieve the element in the cart where the product price is stored
    // for each product in the cart

  }

  function displayCartTotal() {
    // display the total cost in the cart
  }

  
  function removeProduct(productId) {
    // remove product from cart (and from database)
  }

  function checkout() {
    // checkout: just clear the cart and clears from database
    // after user confirms the checkout process
  }

})();