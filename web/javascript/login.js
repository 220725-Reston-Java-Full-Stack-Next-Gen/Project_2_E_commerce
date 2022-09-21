var username =document.getElementById("username")
var password=document.getElementById("password")
var submit=document.getElementById("submit")



var errorMessages = "";

submit.addEventListener("click", async()=> {
    const username_value = username.value
    const password_value = password.value
    const login =await fetch(`http://localhost:8080/users/login`,{

        method: "POST",
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
        
    }).then((response) => {
        console.log(response);
        sessionStorage.setItem("loggedInUser", JSON.stringify(response));

        window.location = "./userAccount.html";
        
    }).catch((error) =>{


        const errorTextMessage = document.getElementById("errorTextMessage");
        errorTextMessage.innerHTML = errorMessages;

        console.error(error);
    })
    
})