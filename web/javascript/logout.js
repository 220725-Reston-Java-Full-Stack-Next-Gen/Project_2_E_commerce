var loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
var logout=document.getElementById("logout")
logout.addEventListener("click", async()=> {
    
    const logout =await fetch(`http://localhost:8080/users/logout`,{

        method: "PUT",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
        
    }).then((response) => {
        console.log(response);
        
        if(!response.ok){
            throw new Error(response.status)
        }
        else{
            return response.json()
        }

    }).then((response) => {
    
        sessionStorage.removeItem("loggedInUser");
        sessionStorage.removeItem("currentUserCart");
        window.location = "/web/html/user_login.html";
    }).catch((error) =>{

            // errorMessages = "";
            // const errorTextMessage = document.getElementById("errorTextMessage");
            // errorTextMessage.innerHTML = errorMessages;

            console.error(error);
        })
    })