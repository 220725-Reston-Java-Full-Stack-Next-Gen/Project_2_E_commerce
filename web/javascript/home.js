const loginBtn = document.getElementById("loginButton");

localStorage.clear();

loginBtn.addEventListener('click', async() => {
    var username = document.getElementById("usernameInput").value;
    var password = document.getElementById("passwordInput").value;

    if (username.trim().length === 0) {
        console.log("Empty Username");
    } else {
        console.log(username);
    }
    if (password.trim().length === 0) {
        console.log("Empty Password");
    } else {
        console.log(password);
    }
    
    if (username.trim().length > 0 && password.trim().length > 0) {
        validateCredentials(username, password);
    }
})

async function validateCredentials(username, password) {
    const resp = await fetch(`http://localhost:8083/Raphael_Gakau_p1/login`, {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: `username=${username}&password=${password}`
    }).then((resp) => {
        console.log(resp.status);
        if (!resp.ok) {
            if (resp.status === 401) {
                console.log("Invalid Username/Password Combination. Please try again.");
            }
            throw new Error(resp.status);
        } 
        return resp.json();
    }).then((user) => {
        console.log(user);
        validateCredentials2(3, 3);
        localStorage.loggedInUser = JSON.stringify(user);
        console.log(localStorage.loggedInUser);
        //window.location.href = "/web/html/admin/test.html";
    }).catch((error) => {
        console.error(error) 
    })
    
}

async function validateCredentials2(id, status) {
    const resp = await fetch(`http://localhost:8083/Raphael_Gakau_p1/update-ticket`, {
        method: 'PUT',
        mode: 'cors',
        credentials: 'include',
        headers: {
            'Content-Type': 'text/plain'
        },
        authorization: 'admin',
        body: `ticketID=${id}&statusID=${status}`
    }).then((resp) => {
        console.log(resp.status);
        console.log(resp.text());
        if (!resp.ok) {
            if (resp.status === 401) {
                console.log("Invalid Username/Password Combination. Please try again.");
            } else if (resp.status === 403) {
                console.log("User not Authenticated.\nPlease log in with your credentials.");
            }
            throw new Error(resp.status);
        } 
        return resp.json();
    }).then((resp) => {
        console.log(resp);
    }).catch((error) => {
        
        console.error(error)
    })
    
}