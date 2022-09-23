(function() {
// VARS
const removeBtn = document.getElementById("btn2");
const checkoutBtn = document.querySelector("right-bar");
const cartContent = document.querySelector("shop");


 // FUNCTIONS

 //Get cart for user
 async function getcartContent() {
    // get contents from database.
    // if nothing is there, create an empty array
    const cartContent = await fetch(`http://localhost:8080/cart`,{

        method: "GET",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
}) || [];
    return cartContent;
  }

//Save cart for user
async function setcartContent(lsContent) {
    // save content inside database
    const cartContent = await fetch(`http://localhost:8080/cart`,{

        method: "POST",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
}).then((response) =>{
    console.log(response);

    if(!response.ok){
        errorMessages = "Error while trying to save cart. Please try again.";
        throw new Error(response.status)
    }
}).catch((error) =>{


    const errorTextMessage = document.getElementById("errorTextMessage");
    errorTextMessage.innerHTML = errorMessages;

    console.error(error);
});
}

//Calculate total for total price section (doesn't include tax right now)
  function calculateTotal(prices) {
       // calculate the total price in the cart
       return prices.reduce(function(prev, next) {
        return prev + next;
      }, 0);
  }

  function getCartItemPrices() {
    // extract the price numbers from the cart items to calculate total
    const prices = [];
    // retrieve the element in the cart where the product price is stored
    // for each product in the cart

  }

  function displayCartTotal() {
     // display the total cost in the cart
    const prices = getCartItemPrices();
    let total = 0;
    if (prices) {
      total = calculateTotal(prices);
      totalPriceContainer.innerHTML = `<span class="total">Total: $${total.toFixed(
        2
      )}</span>`;
    } else {
      totalPriceContainer.innerHTML = '<span class="total">Total: $0</span>';
    }
  }

  function displayCart() {
    // display all products in the cart

    // get contents from local storage
    const cartContent = getcartContent();
    let productMarkup = "";
    // if local storage is not empty, build the
    // cart items markup and the appropriate details
    if (cartContent !== null) {
      for (let product of cartContent) {
        productMarkup += `
          <tr>
          <td><img class="cart-image" src="${image_link}" alt="${
          product_name
        }" width="120"></td>
          <td>
            ${product_name}
          </td>
          <td>${product_price}</td>
          <td><a href="#" data-id="${product_id}" class="remove">X</a></td>
          </tr>
        `;
      }
    } else {
      // if no content is in local storage, alert user
      productMarkup = "Your cart is empty.";
    }
    // add markup to DOM
    cartContent.querySelector("tbody").innerHTML = productMarkup;
  }

  
  function removeProduct(productId) {
    // remove product from cart (and from database)

    // retrieve list of products from database
    const cartContent = getcartContent();

    // get the index of the product item to remove
    // inside the local storage content array
    let cart_item_id;
    cartContent.forEach(function(product, i) {
      if (product_id === product_id) {
        cart_item_id = i;
      }
    });

    // modify the items in local storage array
    // to remove the selected product item

    cartContent.splice(productIndex, 1);
    // update local storage content
    setcartContent(cartContent);

    displayCart();
  }

  function checkout() {
    // checkout: just clear the cart and clears from database
    // after user confirms the checkout process

    //does this need to be done in checkout.js?
  }

  // BIND EVENTS AND CALL FUNCTIONS

  // Page load:
  document.addEventListener("DOMContentLoaded", function(e) {
    // display list of products in cart, if any, on page load
    displayCart();
    // display cart total
    displayCartTotal();
  });

  // bind removeProduct to remove button on html page
  removeBtn.addEventListener("click", async() => {
      // get the value of the data-id property, which contains the
      // id of the selected product
      const productId = removeBtn.getAttribute(await fetch(`http://localhost:8080/cart/cart_id`,{

        method: "GET",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
}));
      // use the id to remove the selected product
      removeProduct(productId);
      // display products in the cart again,
      // now the list should be displayed with 1 less product
      // or empty if no products are left in the cart

      // adjust the total
      displayCartTotal();
  });
})();