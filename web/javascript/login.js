var username =document.getElementById("username")
var password=document.getElementById("password")
var submit=document.getElementById("submit")

submit.addEventListener("click", async()=> {
    const username_value = username.value
    const password_value = password.value
    const login =await fetch(`localhost:8080/users/login`,{

        method: "POST",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          "username":username_value,
          "password":password_value,
        })


    }).then((response) =>{
        console.log(response);
        if(!response.ok){
            throw new Error(response.status)
        }
        else{
            return response.json()
        }
        
    }).then((response) =>{
        console.log(response);

    }).catch((error) =>{
        console.error(error);
    })
    
})