
var searchInput = document.getElementById("searchInput");
var searchBtn = document.getElementById("searchBtn");

searchBtn.addEventListener('click', (event) => {
    event.preventDefault();

    let searchQuery = searchInput.value;

    if (searchQuery.trim().length < 5) {
        alert("Please enter more than 4 characters to search.");
    } else {
        searchQuery = searchQuery.trim();

        
        localStorage.setItem("searchTerm", searchQuery);
        console.log(localStorage.getItem("searchTerm"));
        
        if (localStorage.getItem("searchTerm") !== null) {
            window.location = "/web/html/products.html";
        }
        
    }
})

























var loggedInUser = JSON.parse(sessionStorage.loggedInUser || "{}");

var cartCounter = document.getElementById("cartCounter");



checkCart();

async function checkCart() {

    // check if there is a loggedInUser
    if (Object.keys(loggedInUser).length !== 0) {

        
        const checkCart = await fetch(`http://localhost:8080/api/cart/get-cart`, {
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
                    console.log("No Cart Found");
                }
                throw new Error(response.status);
            } else {
                return response.json();
            }

        }).then((response) => {
            console.log(response);
            
            //cartCounter.innerHTML = `${response.orderQuantity}`;

            sessionStorage.setItem("currentUserCart", JSON.stringify(response));

            let currentUserCart = JSON.parse(sessionStorage.currentUserCart || "{}");
            // check is user has an existing uncheckout cart
            if (Object.keys(currentUserCart).length !== 0) {
                //let cartCounter = document.getElementById("cartCounter");
                cartCounter.innerHTML = `${currentUserCart.orderQuantity}`;
            }


        }).catch((error) => {
            console.error(error);
            sessionStorage.removeItem("currentUserCart");
        });
        
    }
    
}