var loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
console.log(loggedInUser);

var firstname = document.getElementById("firstname");
firstname.value = loggedInUser.firstName;
var lastname = document.getElementById("lastname");
lastname.value =loggedInUser.lastName;
var email= document.getElementById("email");
email.value =  loggedInUser.email;
var phone= document.getElementById("phone");
 phone.value =  loggedInUser.phoneNumber;
var streetaddress = document.getElementById("streetaddress");
streetaddress.value =   loggedInUser.address;
var city = document.getElementById("city");
city.value  =   loggedInUser.city;
var state = document.getElementById("state");
state.value =  loggedInUser.state;
var zipcode = document.getElementById("zipcode");
zipcode.value = loggedInUser.zipcode;
var submit=document.getElementById("submit")


submit.addEventListener("click", async()=> {
    const firstname_value = username.value
    const lastname_value = password.value
    const email_value = email.value
    const phone_value = password.value
    const streetaddress_value = username.value
    const city_value = password.value
    const state_value = username.value
    const zipcode_value = password.value

    const update =await fetch(`http://localhost:8080/users/update`,{
       
        method: "PUT",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          "firstName":`${firstname_value}`,
          "lastName":`${lastname_value}`,
          "email":`${email_value}`,
          "phoneNumber":`${phone_value}`,
          "address":`${streetaddress_value}`,
          "city":`${city_value}`,
          "state":`${state_value}`,
          "zipcode":`${zipcode_value}`,
          
          
        })


    })
}).then((response) => {
    console.log(response);
    sessionStorage.setItem("loggedInUser", JSON.stringify(response));

    window.location = "./home.html";
})