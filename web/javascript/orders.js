var loggedInUser = JSON.parse(sessionStorage.loggedInUser || "{}");


//var shopDiv = document.querySelector('.shop');
// var rightDiv = document.querySelector('.right-bar');
var loader = document.querySelector('.lds-ring');

// check if there is a loggedInUser
if (Object.keys(loggedInUser).length !== 0) {

    getOrders();

} else {
    console.log("User Not Authenticated.\nPlease Log in with your credentials.");
    //window.location.href = "./login.html";
}

async function getOrders() {
    const orders = await fetch(`http://localhost:8080/orders/get-user-orders`, {
        method: 'GET',
        mode: 'cors',
        credentials: 'include',
        headers: {
            'content-type':'application/json'
        }
    }).then((response) => {
        
        console.log(response);

        if (!response.ok) {
            throw new Error(response.status);
        } else {
            return response.json();
        }

    }).then(async (response) => {
        const orderCol = document.getElementById("orderCol");
        for (const order of response) {

            let wholeOrderHolder = document.createElement('div');
            wholeOrderHolder.classList.add("row", "mb-3", "p-3", "whole-order-holder", "border", "pill-button", "bg-light");

            let hCol = document.createElement('div');
            hCol.classList.add("col");

            let rowInfoHolder = document.createElement('div');
            rowInfoHolder.classList.add("row", "order-info-holder");

            let div1 = document.createElement('div')
            div1.classList.add("col-3")
            let dateDiv1 = document.createElement('div');
            dateDiv1.classList.add("row", "justify-content-center");
            let dateP = document.createElement('p');
            dateP.classList.add("no-margin-text");
            dateP.innerHTML = `Order Placed On:`;
            dateDiv1.append(dateP);
            let dateDiv2 = document.createElement('div');
            dateDiv2.classList.add("row", "justify-content-center");
            let dateP2 = document.createElement('p');
            dateP2.classList.add("bold-text");
            dateP2.innerHTML = `${order.dateCreated.substring(0,10)}`
            dateDiv2.append(dateP2);
            div1.append(dateDiv1, dateDiv2);



            let div2 = document.createElement('div')
            div2.classList.add("col-3")
            let countDiv1 = document.createElement('div');
            countDiv1.classList.add("row", "justify-content-center");
            let countP = document.createElement('p');
            countP.classList.add("no-margin-text");
            countP.innerHTML = `Order Item Count:`;
            countDiv1.append(countP);
            let countDiv2 = document.createElement('div');
            countDiv2.classList.add("row", "justify-content-center");
            let countP2 = document.createElement('p');
            countP2.classList.add("bold-text");
            countP2.innerHTML = `${order.orderQuantity} Items`
            countDiv2.append(countP2);
            div2.append(countDiv1, countDiv2);
            
            let div3 = document.createElement('div')
            div3.classList.add("col-3")
            let priceDiv1 = document.createElement('div');
            priceDiv1.classList.add("row", "justify-content-center");
            let priceP = document.createElement('p');
            priceP.classList.add("no-margin-text");
            priceP.innerHTML = `Order Total Price:`;
            priceDiv1.append(priceP);
            let priceDiv2 = document.createElement('div');
            priceDiv2.classList.add("row", "justify-content-center");
            let priceP2 = document.createElement('p');
            priceP2.classList.add("bold-text");
            priceP2.innerHTML = `$${order.orderTotalPrice.toFixed(2)}`
            priceDiv2.append(priceP2);
            div3.append(priceDiv1, priceDiv2);


            let div4 = document.createElement('div')
            div4.classList.add("col-3")
            let statusDiv1 = document.createElement('div');
            statusDiv1.classList.add("row", "justify-content-center");
            let orderP = document.createElement('p');
            orderP.classList.add("no-margin-text");
            orderP.innerHTML = `Order Status:`;
            statusDiv1.append(orderP);
            let statusDiv2 = document.createElement('div');
            statusDiv2.classList.add("row", "justify-content-center");
            let statusP2 = document.createElement('p');
            statusP2.classList.add("bold-text");
            statusP2.innerHTML = `${order.orderStatus.orderStatus.toUpperCase()}`
            statusDiv2.append(statusP2);
            div4.append(statusDiv1, statusDiv2);

            
            let titleSep = document.createElement('div');
            titleSep.classList.add("row", "justify-content-center");
            let bHr = document.createElement('hr');
            bHr.classList.add("border", "border-primary", "m-0", "w-100");
            titleSep.append(bHr);

            rowInfoHolder.append(div1, div2, div3, div4);

            hCol.append(rowInfoHolder, titleSep);
            
            const orderDetails = await fetch(`http://localhost:8080/api/orderdetails/get-details`, {
                method: 'PUT',
                mode: 'cors',
                credentials: 'include',
                headers: {
                    'content-type':'application/json'
                },
                body: JSON.stringify(order)
            }).then((response) => {
                loader.style = "display:none";
                console.log(response);
    
                if (!response.ok) {
                    throw new Error(response.status);
                } else {
                    return response.json();
                }
            }).then((response) => {
                

                for (const detail of response) {
                    let orderProductHolder = document.createElement('div');
                    orderProductHolder.classList.add("row", "justify-content-center", "order-product-holder", "my-2");

                    let leftColDiv = document.createElement('div');
                    leftColDiv.classList.add("col-3");
                    let imageDiv = document.createElement('div');
                    let image = document.createElement('img');
                    image.src = `${detail.product.image_Link}`
                    image.classList.add("rounded-pill", "border", "border-secondary", "order-product-image-holder");
                    imageDiv.append(image);

                    let starsHolderDiv = document.createElement('div');
                    for (let i = 0; i < detail.product.productRating; i++) {
                        let starIcon = document.createElement("i");
                        starIcon.classList.add("fa-solid", "fa-star", "star-rating");
                        starsHolderDiv.append(starIcon);
                    }
                    leftColDiv.append(imageDiv, starsHolderDiv);


                    let centerColDiv = document.createElement('div');
                    centerColDiv.classList.add("col-6");
                    
                    let titleHolderDiv = document.createElement('div');
                    titleHolderDiv.classList.add("row");
                    let titleH5 = document.createElement('h5');
                    titleH5.innerHTML = `${detail.product.productName}`
                    titleHolderDiv.append(titleH5);

                    let buyItAgainDiv = document.createElement('div');
                    buyItAgainDiv.classList.add("row", "align-items-center");
                    let priceH5 = document.createElement('h5');
                    priceH5.classList.add("bold-text", "mr-4");
                    priceH5.innerHTML = `$${detail.product.productPrice.toFixed(2)}`;
                    let buyItAgainBtn = document.createElement('button');
                    buyItAgainBtn.classList.add("btn", "btn-secondary", "pill-button", "px-3");
                    buyItAgainBtn.innerHTML = `<i class="fa-solid fa-arrow-rotate-right"></i>&nbsp;&nbsp;&nbsp;Buy it again`;
                    buyItAgainDiv.append(priceH5, buyItAgainBtn);

                    centerColDiv.append(titleHolderDiv, buyItAgainDiv)

                    let rightColDiv = document.createElement('div');
                    rightColDiv.classList.add('col-3');
                    let trackDiv = document.createElement('div');
                    trackDiv.classList.add("row", "justify-content-center", "mb-3");
                    let trackBtn = document.createElement('button');
                    trackBtn.classList.add("btn", "btn-secondary", "pill-button", "px-3");
                    trackBtn.innerHTML = `<i class="fa-solid fa-truck"></i>&nbsp;&nbsp;&nbsp;&nbsp;Track Package`;
                    trackDiv.append(trackBtn);
                    let reviewDiv = document.createElement('div');
                    reviewDiv.classList.add("row", "justify-content-center", "mb-3");
                    let reviewBtn = document.createElement('button');
                    reviewBtn.classList.add("btn", "btn-secondary", "pill-button", "px-3");
                    reviewBtn.innerHTML = `<i class="fa-solid fa-star"></i>&nbsp;&nbsp;Write Product Review`;
                    reviewDiv.append(reviewBtn);
                    rightColDiv.append(trackDiv, reviewDiv);

                    orderProductHolder.append(leftColDiv, centerColDiv, rightColDiv);

                    let separatorDiv = document.createElement('div');
                    separatorDiv.classList.add("row", "justify-content-center");
                    let sepHR = document.createElement('hr');
                    sepHR.classList.add("border", "border-primary", "m-0", "w-100", "dashed-border");
                    separatorDiv.append(sepHR);

                    hCol.append(orderProductHolder, separatorDiv);
                }
    
                wholeOrderHolder.append(hCol);
            }).catch((error) => {
                console.error(error);
            })
            
            orderCol.append(wholeOrderHolder);
        }

    })
}












async function getDetails(orders) {
    for (const order of orders) {
        const orderDetails = await fetch(`http://localhost:8080/api/orderdetails/get-details`, {
            method: 'GET',
            mode: 'cors',
            credentials: 'include',
            headers: {
                'content-type':'application/json'
            }
        }).then((response) => {
            console.log(response);

            if (!response.ok) {
                throw new Error(response.status);
            } else {
                return response.json();
            }
        }).then((response) => {
            
            generateData(response);

        }).catch((error) => {
            console.error(error);
        })


        let orderProductHolder = document.createElement('div');
        let leftColDiv = document.createElement('div');
        let imageDiv = document.createElement('div');
        let image = document.createElement('img');
        image.src = `${order}`
    }
}


async function generateData(orderDetails) {
    for (const detail of orderDetails) {

    }
}


