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
    const firstname_value = firstname.value
    const lastname_value = lastname.value
    const email_value = email.value
    const phone_value = phone.value
    const streetaddress_value = streetaddress.value
    const city_value = city.value
    const state_value = state.value
    const zipcode_value = zipcode.value

    var newUser = {
        "firstName":`${firstname_value}`,
        "lastName":`${lastname_value}`,
        "email":`${email_value}`,
        "phoneNumber":`${phone_value}`,
        "address":`${streetaddress_value}`,
        "city":`${city_value}`,
        "state":`${state_value}`,
        "zipcode":`${zipcode_value}`,
        "id":`${loggedInUser.id}`,
        "userName":`${loggedInUser.userName}`,
        "password":`${loggedInUser.password}`,
        "userRole":{
        "id":`${loggedInUser.userRole.id}`,
        "role":`${loggedInUser.userRole.role}`
        },
        "dateCreated":`${loggedInUser.dateCreated}`  
    }

    const update =await fetch(`http://localhost:8080/users/update`,{
       
        method: "PUT",
        credentials: 'include',
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newUser)

    }).then((response) => {
        console.log(response);
        if (!response.ok) {
            if (response.status === 403) { // User is currently not logged in.
                console.log("Not Authenticated. Please log in with your credentials");
            } else if (response.status === 500) { // Other backend error.
                console.log("Server Error. Please try again.")
            }
            throw new Error(response.status);
        } else {
            return response.json();
        }
    }).then((response) => {
        console.log(response);
        if (response.message.startsWith("Something went wrong")) {
            console.log(response.message);
        } else {
            sessionStorage.setItem("loggedInUser", JSON.stringify(newUser));
        }
        

        window.location = "./home.html";
    }).catch((error) => {
        console.error(error);
    })
})
