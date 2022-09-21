
var form = document.getElementById("createAcctForm");

var errorMessage ="";


form.addEventListener('submit', async(event)=>{
    event.preventDefault(); //prevent form from auto submitting

    const username = document.getElementById("userName").value;
    const psw = document.getElementById("password").value;
    const firstname = document.getElementById("firstName").value;
    const lastname = document.getElementById("lastName").value;
    const address = document.getElementById("address").value;
    const state = document.getElementById("state").value;
    const city = document.getElementById("city").value;
    const zipcode = document.getElementById("zipcode").value;
    const phonenumber = document.getElementById("phoneNumber").value;
    const email = document.getElementById("email").value;
   
    try{
        const createAcct = await fetch(`http://localhost:8080/users/register`,{

            method:"POST",
            headers: {
                Accept:"application/json, text/plain, */*",
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "userName":`${username}`,
                "password": `${psw}`,
                "firstName":`${firstname}`,
                "lastName":`${lastname}`,
                "address":`${address}`,
                "state":`${state}`,
                "city":`${city}`,
                "zipcode":`${zipcode}`,
                "phoneNumber":`${phonenumber}`,
                "email":`${email}`
            })
        }).then((response) =>{
            console.log(response);

            if(!response.ok){
                errorMessage = "Error while trying to create an account. Please try again!";
                throw new Error(response.status);
            }else{
                response.json();
                window.alert("Account Created Successfully.");
                window.location.href="user_login.html";
                
            } 
        })  
    }catch(error){
        const errorTextMessage = document.getElementById("errorTextMessage");
        alert(errorTextMessage);
        console.error(error);
    }
});