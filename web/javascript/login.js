sessionStorage.clear();

var username =document.getElementById("username")
var password=document.getElementById("password")
var submit=document.getElementById("submit")




var errorMessages = "";

submit.addEventListener("click", async(event)=> {
    event.preventDefault();
    const username_value = username.value
    const password_value = password.value
    const login =await fetch(`http://localhost:8080/users/login`,{

        method: "POST",
        mode: 'cors',
        credentials: 'include',
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          "userName":`${username_value}`,
          "password":`${password_value}`,
        })


    }).then((response) =>{
        console.log(response);

        if(!response.ok){
            errorMessages = "Error while trying to login. Please try again.";
            throw new Error(response.status)
        }
        else{
            return response.json()
        }
        
    }).then( async (response) => {
        console.log(response);
        sessionStorage.setItem("loggedInUser", JSON.stringify(response));

        

        window.location = "/web/html/userAccount.html";
        
    }).catch((error) =>{


        const errorTextMessage = document.getElementById("errorTextMessage");
        errorTextMessage.innerHTML = errorMessages;

        console.error(error);
    })
    
})

async function getCart() {
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
            
            cartCounter.innerHTML = `${response.orderQuantity}`;

            sessionStorage.setItem("currentUserCart", JSON.stringify(response));

        }).catch((error) => {
            console.error(error);
        });
}