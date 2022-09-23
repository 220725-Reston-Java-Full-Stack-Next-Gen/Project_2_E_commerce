<<<<<<< HEAD
var loggedInUser = JSON.parse(sessionStorage.loggedInUser || "{}");

var shopDiv = document.querySelector('.shop');
var loader = document.querySelector('.lds-ring');
var rightDiv = document.querySelector('.right-bar');
// check if there is a loggedInUser
if (Object.keys(loggedInUser).length !== 0) {

    // check is user has an existing uncheckout cart
    var currentUserCart = JSON.parse(sessionStorage.currentUserCart || "{}");

    if (Object.keys(currentUserCart).length !== 0) {
        
        //cartCounter.innerHTML = `${currentUserCart.orderQuantity}`;
        
        getCartItems();

    } else {
        console.log(`${currentUserCart.length}`);
        shopDiv.innerHTML = `<h4>Cart is empty.</h4>`;
    }
} else {
    console.log("User Not Authenticated.\nPlease Log in with your credentials.");
    window.location.href = "/web/html/user_login.html";
}

async function generate(cartItems) {

    let subtotalPrice = 0;
    let tax = 0;
    let shippingPrice = 0;
    let totalPrice = 0;

    for (const item of cartItems) {
        let boxDiv = document.createElement('div');
        boxDiv.classList.add("box");
        let itemImage = document.createElement('img');
        itemImage.width = `200px`;
        
        itemImage.src = `${item.product.image_Link}`;
        let contentDiv = document.createElement('div');
        contentDiv.classList.add("content");
        
        let itemTitle = document.createElement('h3');
        itemTitle.innerHTML = `${item.product.productName}`;

        let itemPrice = document.createElement('h4');
        itemPrice.innerHTML = `Price: $${item.product.productPrice.toFixed(2)}`;

        let quantityP = document.createElement('p');
        quantityP.innerHTML = `Quantity: `;
        let quantityInput = document.createElement("input");
        quantityInput.value = `${item.productQuantity}`;
        quantityInput.name = `quantity`;
        

        quantityP.append(quantityInput);
        quantityP.classList.add("unit");

        let removeBtn = document.createElement('p');
        removeBtn.innerHTML = `<i aria-hidden="true" class="fa fa-trash"></i> <span class="btn2">Remove</span>`;
        removeBtn.classList.add("btn-area");

        removeBtn.addEventListener('click', async() => {
            removeCartItem(item);
        });

        contentDiv.append(itemTitle, itemPrice, quantityP, removeBtn);
        
        boxDiv.append(itemImage, contentDiv);
        
        shopDiv.append(boxDiv);

        let price = `${item.product.productPrice}`;
        let quantity = `${item.productQuantity}`;

        subtotalPrice += (Number(price) * Number(quantity));
        shippingPrice += 2;
    }

    

    tax = subtotalPrice * 0.06;
    totalPrice = subtotalPrice + tax + shippingPrice;

    var subtotalPriceHolder = document.getElementById("subtotalPrice");
    var taxHolder = document.getElementById("tax");
    var shippingHolder = document.getElementById("shipping");
    var totalPriceHolder = document.getElementById("totalPrice");


    subtotalPriceHolder.innerHTML = `$${subtotalPrice.toFixed(2)}`;
    taxHolder.innerHTML = `$${tax.toFixed(2)}`;
    shippingHolder.innerHTML = `$${shippingPrice.toFixed(2)}`;
    totalPriceHolder.innerHTML = `$${totalPrice.toFixed(2)}`;
}


async function getCartItems() {
    let idS = `${currentUserCart.cartID}`;
    let id = Number(idS);
    const checkCart = await fetch(`http://localhost:8080/api/cart/get-cart-items?cartID=${id}`, {
        method: 'GET',
        more: 'cors',
        credentials: 'include',
        headers: {
            'content-type':'application/json'
        }
    }).then((response) => {
        console.log(response);

        if (!response.ok) {
            if (response.status === 401) {
                console.log("Not Authenticated");
                //window.location = "/web/html/user_login.html";
                
            } else if (response.status === 400) {
                console.log("No Cart Items Found");
            }
            throw new Error(response.status);
        } else {
            return response.json();
        }

    }).then((response) => {
        generate(response);
        loader.style = "display:none";
        rightDiv.setAttribute("style", "display: block !important")
        console.log(response);
        
        //cartCounter.innerHTML = `${response.orderQuantity}`;

        sessionStorage.setItem("currentUserCart", JSON.stringify(response))

        

    }).catch((error) => {
        sessionStorage.removeItem("currentUserCart");
        console.error(error);
=======
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
>>>>>>> e815d71ac51157e6eef8d289fdd75bdd7e23c939
    });
}


<<<<<<< HEAD
async function removeCartItem(cartItem) {
    console.log(`${cartItem.product.productName}`);
    const checkCart = await fetch(`http://localhost:8080/api/cart/get-cart-items?cartID=${id}`, {
        method: 'GET',
        more: 'cors',
        credentials: 'include',
        headers: {
            'content-type':'application/json'
        }
    }).then((response) => {
        console.log(response);

        if (!response.ok) {
            if (response.status === 401) {
                console.log("Not Authenticated");
                //window.location = "/web/html/user_login.html";
                
            } else if (response.status === 400) {
                console.log("No Cart Items Found");
            }
            throw new Error(response.status);
        } else {
            return response.json();
        }

    }).then((response) => {
        generate(response);
        loader.style = "display:none";
        rightDiv.setAttribute("style", "display: block !important")
        console.log(response);
        
        //cartCounter.innerHTML = `${response.orderQuantity}`;

        sessionStorage.setItem("currentUserCart", JSON.stringify(response))

        

    }).catch((error) => {
        sessionStorage.removeItem("currentUserCart");
        console.error(error);
    });
}

var checkoutBtn = document.getElementById("checkoutBtn");
=======
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
>>>>>>> e815d71ac51157e6eef8d289fdd75bdd7e23c939
