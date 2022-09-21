var loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
console.log(loggedInUser);

var firstname = document.getElementById("firstname");
firstname.value = loggedInUser.firstName;

