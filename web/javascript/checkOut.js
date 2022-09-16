  
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
 