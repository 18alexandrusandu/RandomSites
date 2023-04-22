$(document).ready(function(){

$('#processing').hide();
$('#title h4 span').hide();
var clicked=false;


$("#auth").click(function(){

/*
    $('#processing').show();

    setTimeout(function delayStart() {
        $('#processing').hide();
        username=document.getElementById("username").value
        password=document.getElementById("password").value
        message=document.getElementById("msg")
        message2=document.getElementById("message")
        i=document.getElementById("icon")

      
     

        if(username!="admin" || password!="admin1234")
          {
            console.log("here")
            message2.classList.remove("chat_valid")   
            message.textContent="Invalid username or password!"
            message2.classList.add("chat_invalid")
            i.classList.remove("fa-lock")
            i.classList.remove("fa-check")
            i.classList.add("fa-exclamation-triangle")

          }
          else
          {
            message.textContent="Authentification succesfull!"
            message2.classList.remove("chat_invalid")   
            message2.classList.add("chat_valid")   

            console.log(i,i.classList)
            i.classList.remove("fa-lock")
            i.classList.remove("fa-exclamation-triangle")
            i.classList.add("fa-check")

          }







      }, 3000);*/
});








$("#title").click(function(){
   if(clicked)
   {
   //hide
    $("#chat").animate(
        {height:"80px"},500
           ,
           function()
           {
            $('#title h4 span').hide()
           }
    
        ).animate(
            {width:"40px"},500
            ,
            function()
            {
             $('#title h4 span').hide()
            }
    
    
        )
   }else
   {
    //show
    $("#chat").animate(
    {width:"300px"},500
       ,
       function()
       {
        $('#title h4 span').show()
       }

    ).animate(
        {height:"900px"},500
        ,
        function()
        {
         $('#title h4 span').show()
        }


    )
  
   }
  clicked=!clicked

})


})