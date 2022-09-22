const loginBtn = document.getElementById("loginButton");

loginBtn.addEventListener('click', async() => {
    var idInput = document.getElementById("ticketID").value;
    //var status = document.getElementById("statusInput").value;

    if (idInput.trim().length === 0) {
        console.log("Empty Username");
    } else {
        console.log(idInput);
    }
    // if (status.trim().length === 0) {
    //     console.log("Empty Password");
    // } else {
    //     console.log(status);
    // }
    
    if (idInput.trim().length > 0) {
        getUser(idInput);
    }
});

async function getUser(id) {
    const resp = await fetch(`http://localhost:8083/api/user/getById?id=${id}`, {
        method: 'GET',
        mode: 'cors', 
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((resp) => {
        console.log(resp.status);
        console.log(resp)
        if (!resp.ok) {
            if (resp.status === 401) {
                console.log("Invalid Username/Password Combination. Please try again.");
            } else if (resp.status === 403) {
                console.log("User not Authenticated.\nPlease log in with your credentials.");
            } else if (resp.status === 404) {
                console.log(`No user with ID: ${id} was found`)
            }
            throw new Error(resp.status);
        } 
        return resp.json();
    }).then((resp) => {
        console.log(resp);
        getUser2();
    }).catch((error) => {
        console.error(error);
    });
}


async function getUser2() {
    const resp = await fetch(`http://localhost:8083/api/candy/findById?id=1`, {
        method: 'GET',
        mode: 'cors', 
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((resp) => {
        console.log(resp.status);
        console.log(resp)
        if (!resp.ok) {
            if (resp.status === 401) {
                console.log("Invalid Username/Password Combination. Please try again.");
            } else if (resp.status === 403) {
                console.log("User not Authenticated.\nPlease log in with your credentials.");
            } else if (resp.status === 404) {
                console.log(`No user with ID: was found`)
            }
            throw new Error(resp.status);
        } 
        return resp.json();
    }).then((resp) => {
        console.log(resp);
    }).catch((error) => {
        console.error(error);
    });
}

// loginBtn.addEventListener('click', async() => {
//     var idInput = document.getElementById("ticketID").value;
//     var status = document.getElementById("statusInput").value;

//     if (idInput.trim().length === 0) {
//         console.log("Empty Username");
//     } else {
//         console.log(idInput);
//     }
//     if (status.trim().length === 0) {
//         console.log("Empty Password");
//     } else {
//         console.log(status);
//     }
    
//     if (idInput.trim().length > 0 && status.trim().length > 0) {
//         validateCredentials(idInput, status);
//     }
// });

async function validateCredentials(id, status) {
    const resp = await fetch(`http://localhost:8083/api/candy/update`, {
        method: 'PUT',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "id": `${id}`,
            "name": `${status}`,
            "price": 12.99,
            "shop": {
                "shopId": 1,
                "shopName": "Walmart",
                "inventoryCount": 10
            }
        })   
    }).then((resp) => {
        console.log(resp.status);
        if (!resp.ok) {
            if (resp.status === 401) {
                console.log("Invalid Username/Password Combination. Please try again.");
            } else if (resp.status === 403) {
                console.log("User not Authenticated.\nPlease log in with your credentials.");
            } else if (resp.status === 404) {
                console.log(`No candy with ID: ${id} was found`)
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