// VARS
var cartProducts = document.getElementById("cart-item");
const removeBtn = document.getElementById("btn2");
const checkoutBtn = document.querySelector("right-bar");

var loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
console.log(loggedInUser);


 // FUNCTIONS

 //Get cart for user
 async function getcartContent() {
    // get contents from database.
    // if nothing is there, create an empty array
    const cartContent = await fetch(`http://localhost:8080/cart/get-cart?userID=${loggedInUser.id}`,{

        method: "GET",
        mode: 'cors',
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
}).then((response) =>{
    console.log(response);
    
    if(!response.ok){
        if(response.status === 404) {
            console.log("Error while trying to retrieve products. Error 404");
            return Promise.reject('error 404')
        }
        else{
            errorMessages = "Error while trying to retrieve products. Please try again.";
            throw new Error(response.status)
        }
        
    }
    else{
        return response.json();
    }
    
  }).then((response) => {
    console.log(response);
    sessionStorage.setItem("Cart", JSON.stringify(response));

    window.location = "./cart.html";
    
});
  return cartContent;
}

// cart array
//getcartContent();
//let cart2 = JSON.parse(sessionStorage.getItem("Cart"));
//console.log(cart2);


//Get cart items
//async function getcartItemsContent() {
    // get contents from database.
    //const cartItemsContent = await fetch(`http://localhost:8080/cart/get-cart-items?cartID=${cart2.cartID}`,{

    //    method: "GET",
    //    mode: 'cors',
    //    headers: {
     //     Accept: "application/json, text/plain, */*",
    //      "Content-Type": "application/json",
    //    },
//}).then((response) =>{
   // console.log(response);
    
   // if(!response.ok){
  //      if(response.status === 404) {
   //         console.log("Error while trying to retrieve products. Error 404");
    //        return Promise.reject('error 404')
    //    }
    //    else{
    //        errorMessages = "Error while trying to retrieve products. Please try again.";
    //        throw new Error(response.status)
    //    }
        
 //   }
 //   else{
 //       return response.json();
 //   }
 //   
//  }).then((response) => {
 //   console.log(response);
//    sessionStorage.setItem("Cart2", JSON.stringify(response));

//    window.location = "./cart.html";
    
//});
 // return cartItemsContent;
//}

//fetching cart items
//getcartItemsContent();
//let cart1 = JSON.parse(sessionStorage.getItem("Cart2"));
//console.log(cart1);    


updateCart();

// update cart
function updateCart() {
   displayCart();
    //renderSubtotal();
  
    // save cart to local storage
    //sessionStorage.setItem("Cart", JSON.stringify(cart));
  //}
}
   //Display the cart
   function displayCart() {
    // display all products in the cart

    // get contents from local storage
    //const cartContent = getcartContent();
    if(cart2.length != 0){
        console.log("Theres stuff")
        return cartProducts.innerHTML = cart2.map((x)=>{
            console.log(x);
            let {cartID, item} = x;
            return `
            <div class = "box">
				<img src="${product.image_Link}" alt = "${item.product_name}">
				<div class="content">
					<h3>${item.product_name}</h3>
					<h4>Price: ${item.product_price}</h4>
					<p class="unit">Q uantity: <input name="" value="${item.product_quantity}"></p>
					<p class="btn-area"><i aria-hidden="true" class="fa fa-trash"></i> <span class="btn2">Remove</span></p>
				</div>
			</div>
        `;
        })
        .join("");
    }
    else{
        console.log("Nothing")
    }
    cart.forEach( (item) => {
        if(item === undefined){
            return;
        }
        else{

        }
        cartProducts.innerHTML += `
            <div class = "box">
				<img src="${product.image_Link}" alt = "${item.product_name}">
				<div class="content">
					<h3>${item.product_name}</h3>
					<h4>Price: ${item.product_price}</h4>
					<p class="unit">Quantity: <input name="" value="${item.product_quantity}"></p>
					<p class="btn-area"><i aria-hidden="true" class="fa fa-trash"></i> <span class="btn2">Remove</span></p>
				</div>
			</div>
        `
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


  
  function removeProduct(productId) {
    // remove product from cart (and from database)

    // retrieve list of products from database
    //const cartContent = getcartContent();

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
  })