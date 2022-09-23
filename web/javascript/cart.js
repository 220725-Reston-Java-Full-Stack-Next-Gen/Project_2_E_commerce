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
    });
}


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
