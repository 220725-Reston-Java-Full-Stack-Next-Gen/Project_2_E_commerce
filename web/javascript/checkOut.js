var loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
var billing=document.getElementById("billing-btn")

billing.addEventListener("click", async()=> {
    const add_order =await fetch(`http://localhost:8080/orders/add`,{

        method: "POST",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
       
         
        })
        $(document).ready(function(){
            $("#shipping-btn").click(function(){
                  $(".nav-tabs a[href='#billing']").tab("show")
            });
          });
          
          
          $(document).ready(function(){
            $("#billing-btn").click(function(){
                  $(".nav-tabs a[href='#order']").tab("show")
                  
            });
          });
          
          $(document).ready(function(){
            $("#order-btn").click(function(){
                  $(".nav-tabs a[href='#payment']").tab("show")
            });
          });
          
          
          function calcfunc(){
           const price = document.getElementById("p1");
           var p= 17;
            price.innerHTML =`Price: $${p}`;
          }
          
          calcfunc();


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

        window.location = "./orders.html";

    }).catch((error) =>{
        console.error(error);
    })
    
  
   