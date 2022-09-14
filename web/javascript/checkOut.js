(function() {
    'use strict';
    window.addEventListener('load', function() {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  })();

  
  function bootstrapTabControl(){
    
      var i, items = $('.nav-link'), pane = $('.tab-pane');
    
      // next
    
      $('.nexttab').on('click',function(){
  
          for(i = 0; i < items.length; i++){
    
              if($(items[i]).hasClass('active') ==true){
  
                  break;
    
              }
  
          }
    
          if(i < items.length - 1){
  
              // for tab
  
              $(items[i]).removeClass('active');
  
              $(items[i+1]).addClass('active');
  
              // for pane
    
              $(pane[i]).removeClass('show active');
    
              $(pane[i+1]).addClass('show active');
  
          }
  
     
    
      });
    }
    bootstrapTabControl();