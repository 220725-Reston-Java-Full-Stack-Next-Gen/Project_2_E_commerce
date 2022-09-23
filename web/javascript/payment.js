var loggedInUser = JSON.parse(sessionStorage.loggedInUser || "{}");

var placeOrderBtn = document.getElementById("placeOrderBtn");

// check if there is a loggedInUser
if (Object.keys(loggedInUser).length !== 0) {

    // check is user has an existing uncheckout cart
    var currentUserCart = JSON.parse(sessionStorage.currentUserCart || "{}");

    if (Object.keys(currentUserCart).length !== 0) {
        cartCounter.innerHTML = `${currentUserCart.orderQuantity}`;
        
        placeOrderBtn.addEventListener('click', (event) => {
            event.preventDefault();
            placeOrderFake();
        })

    } else {
        shopDiv.innerHTML = `<h4>Cart is Empty</h4>`;
    }
} else {
    console.log("User Not Authenticated.\nPlease Log in with your credentials.");
    //window.location.href = "./login.html";
}


async function placeOrderFake() {

    const response = await fetch(`http://localhost:8080/orders/add`, {
        method: 'POST',
        mode: 'cors',
        credentials: 'include',
        headers: {
            'content-type':'application/json'
        },
        body: JSON.stringify({
            "orderQuantity":`${currentUserCart.orderQuantity}`
        })
    }).then((response) => {
        console.log(response);

        if (!response.ok) {
            throw new Error(response.status);

        } else {
            return response.json();

        }
    }).then(async (response) => {
        console.log(response);


    }).catch((error) => {
        console.error(error);
    })
}



/** DO NOT CALL THIS METHOD. THE BACKEND WILL NOT ALLOW SENDING PAYMENTS USING HTTP PROTOCOL AND WILL CRASH */
async function placeOrder() {
    var cardNameInput = document.getElementById("cardName");
    var cardTypeInput = document.getElementById("cardType");
    var cardNumberInput = document.getElementById("cardNumber");
    var cvvInput = document.getElementById("cvvInput");
    var expDateInput = document.getElementById("expDate");


    let cardName = cardNameInput.value;
    let cardType = cardTypeInput.value;
    let cardNumber = cardNumberInput.value;
    let cvv = cvvInput.value;
    let expDate = expDateInput.value;

    const response = await fetch(`http://localhost:8080/api/payment/add`, {
        method: 'POST',
        mode: 'cors',
        credentials: 'include',
        headers: {
            'content-type':'application/json'
        },
        body: JSON.stringify({
            "paymentType":`${cardType}`,
            "paymentNumber":`${cardNumber}`,
            "paymentExpDate":`${expDate}`,
            "paymentCvv":`${cvv}`,
        })
    }).then((response) => {
        console.log(response);

        if (!response.ok) {
            throw new Error(response.status);

        } else {
            return response.json();

        }
    }).then(async (response) => {
        console.log(response);


    }).catch((error) => {
        console.error(error);
    })
}
