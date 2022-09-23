
var loggedInUser = JSON.parse(sessionStorage.loggedInUser || "{}");

//let cartCounter = document.getElementById("cartCounter");

// check if there is a loggedInUser
if (Object.keys(loggedInUser).length !== 0) {

    // check is user has an existing uncheckout cart
    var currentUserCart = JSON.parse(sessionStorage.currentUserCart || "{}");

    if (Object.keys(currentUserCart).length !== 0) {
        cartCounter.innerHTML = `${currentUserCart.orderQuantity}`;
    }
}




var allHolderDiv = document.getElementById("allHolderDiv");
//console.log(singleRowProducts);

let loader = `<div class="lds-ring"><div></div><div></div><div></div><div></div></div>`;
allHolderDiv.innerHTML = loader;

// async function checkPic(link) {
//     //console.log(`${link}`)
//     const valid = await fetch (`${link}`, {
//         method: "GET",
//         mode: 'no-cors'
//     }).then((response) => {
//         console.log(response.status);
        
//         if (response.status != 0) {
//             throw new Error(response.status);
//         } else {
//             return true;
//         }
//     }).then((response) => {
//         //console.log(response);
//         return true;
//     }).catch((error) => {
//         //console.error(error);
//         return false;
//     }) 
// }
async function getNailPolishProducts() {
    const getProducts = await fetch(`http://localhost:8080/api/products/products-by-type?type=bronzer`,{
        method: "GET",
        mode: 'cors',
        credentials:'include',
        headers: {
            'content-type':'application/json'
        },
    }).then((response) =>{
        console.log(response);

        if(!response.ok){
            errorMessages = "Error while trying to retrieve products. Please try again.";
            throw new Error(response.status)
        }
        else{
            return response.json()
        }
        
    }).then(async (response) => {
        allHolderDiv.innerHTML = "";
        //console.log(response);
        
        //var singleRowProducts = document.getElementsByClassName("single-row-products");
        let count = 0;
        let singleRowProductsHolder = document.createElement("div");
        singleRowProductsHolder.classList.add("row", "align-items-start", "justify-content-center", "mb-3", "single-row-products", "d-none");
        count = response.length;

        for (const product of response) {
            
            let newProductHolderDiv = document.createElement("div");
            if (count > 3) {
                newProductHolderDiv.classList.add("col-md-2", "col-12", "bg-light", "m-3", "p-3", "product-holder-template");
            } else {
                newProductHolderDiv.classList.add("col-md-4", "col-12", "bg-light", "m-3", "p-3", "product-holder-template");
            }
            
            
            let imageHolderDiv = document.createElement("div");
            let imageLink = document.createElement("a");
            let productImage = document.createElement("img");

            // if (await checkPic(`${product.image_Link}`) === true) {
            //     productImage.src = `${product.image_Link}`;
            // } else {
            //     //console.log(false);
            //     productImage.src = "https://d3t32hsnjxo7q6.cloudfront.net/i/c2fcd605bbf3941b521fb74bfa942ac6_ra,w158,h184_pa,w158,h184.png";
            // }
            productImage.src = `${product.image_Link}`;
            
            productImage.classList.add("rounded-pill", "border", "border-secondary", "product-image-holder");
            
            imageLink.classList.add("d-flex", "justify-content-center");
            
            imageHolderDiv.classList.add("row", "justify-content-center");

            imageLink.append(productImage);
            imageHolderDiv.append(imageLink);

            let starsHolderDiv = document.createElement("div");
            starsHolderDiv.classList.add("row", "justify-content-center", "mb-3");

            for (let i = 0; i < product.productRating; i++) {
                let starIcon = document.createElement("i");
                starIcon.classList.add("fa-solid", "fa-star", "star-rating");
                starsHolderDiv.append(starIcon);
            }

            let productNameH4 = document.createElement("h4");
            productNameH4.classList.add("text-center", "product-name");
            productNameH4.innerHTML = `${product.productName}`;

            let productNameH5 = document.createElement("h5");
            productNameH5.classList.add("text-center", "price-text");
            productNameH5.innerHTML = `$${product.productPrice.toFixed(2)}`;

            let productColorP = document.createElement("p");
            productColorP.classList.add("text-center", "product-color");
            productColorP.innerHTML = `Color: ${product.productColor.colorName}`;

            //let productColorBox = document.createElement(`<div class="color-box"></div>`);
            let productColorBox = document.createElement("div");
            let productColor = document.createElement("div");
            productColorBox.classList.add("d-flex", "justify-content-center", "align-self-center");
            productColor.classList.add("color-box", "d-flex", "justify-content-center", "align-self-center");

            productColor.style.backgroundColor = `${product.productColor.colorHexValue}`;
            productColorBox.append(productColor);


            let productHR = document.createElement("hr");
            productHR.classList.add("my-2");
            let productHR2 = document.createElement("hr");
            productHR.classList.add("m-0");

            let buttonHolderDiv = document.createElement("div");
            buttonHolderDiv.classList.add("row", "justify-content-center", "mt-4");
            let addToCartbutton = document.createElement("button");
            addToCartbutton.innerHTML = "Add To Cart"
            addToCartbutton.classList.add("btn", "btn-secondary", "mb-2", "px-4");

            addToCartbutton.addEventListener('click', async() => {
                addToCartbutton.classList.remove("btn-secondary");
                addToCartbutton.classList.add("btn-success");
                createCart(product);
            });
            buttonHolderDiv.append(addToCartbutton);

            newProductHolderDiv.append(imageHolderDiv, starsHolderDiv, productNameH4, productNameH5, productHR, productColorBox, productColorP, productHR2, buttonHolderDiv);
            singleRowProductsHolder.append(newProductHolderDiv);

            singleRowProductsHolder.classList.add("row", "align-items-start", "justify-content-center", "mb-3", "single-row-products", "d-none")
            
            allHolderDiv.append(singleRowProductsHolder);
            singleRowProductsHolder.setAttribute("style", "display: flex !important");
        }
        
        //allHolderDiv.append(singleRowProductsHolder);

    }).catch((error) =>{


        // const errorTextMessage = document.getElementById("errorTextMessage");
        // errorTextMessage.innerHTML = errorMessages;

        console.error(error);
    })
}

getNailPolishProducts();

async function createCart(product) {

    // check if there is a loggedInUser
    if (Object.keys(loggedInUser).length !== 0) {

        const checkCart = await fetch(`http://localhost:8080/api/cart/add-to-cart`, {
            method: 'POST',
            more: 'cors',
            credentials: 'include',
            headers: {
                'content-type':'application/json'
            },
            body: JSON.stringify(product)
        }).then((response) => {
            console.log(response);

            if (!response.ok) {
                if (response.status === 401) {
                    //window.location = "/web/html/user_login.html";
                }
                throw new Error(response.status);
            } else {
                return response.json();
            }

        }).then((response) => {
            console.log(response);
            
            cartCounter.innerHTML = `${response.orderQuantity}`;

            sessionStorage.setItem("currentUserCart", JSON.stringify(response));

        }).catch((error) => {
            console.error(error);
        });

    } else {
        console.log("User Not Authenticated.\nPlease Log in with your credentials.");
        //window.location.href = "./login.html";
    }
    
}


async function addToCartFunctionality(product) {
    console.log(`CART ${product.productPrice}`);
    const getProducts = await fetch(`http://localhost:8080/api/cart/add-to-cart`,{
        method: "POST",
        mode: 'cors',
        credentials: 'include',
        headers: {
            'content-type':'application/json'
        },
        body: JSON.stringify(product)
    }).then((response) =>{
        console.log(response);

        if(!response.ok){
            errorMessages = "Error while trying to add item to cart. Please try again.";
            throw new Error(response.status)
        }
        else{
            return response.json()
        }
        
    }).then((response) => {
        console.log(response);
    }).catch((error) => {
        console.log(error);
    })

}