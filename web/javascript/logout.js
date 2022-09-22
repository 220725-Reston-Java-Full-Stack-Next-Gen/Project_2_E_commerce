var loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
var logout=document.getElementById("logout")
logout.addEventListener("click", async()=> {
    
    const logout =await fetch(`localhost:8080/users/logout`,{

        method: "PUT",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
        
    }).then((response) => {
        console.log(response);
        sessionStorage.removeItem("loggedInUser", JSON.stringify(response));

        window.location = "./login.html";
        
    }).catch((error) =>{


        const errorTextMessage = document.getElementById("errorTextMessage");
        errorTextMessage.innerHTML = errorMessages;

        console.error(error);
    })
})