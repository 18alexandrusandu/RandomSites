$("document").ready(function()
{
$("#welcome").hide();

$("#conversation").hide();


$("#sendMessageButton").on("click",sendMsg)



$("#globalLogout").on("click",globalLogout)

$("#readMessages").on("click",readMsg)


$("#welcome i").on("click",logout)


$("#auth").on("click",function()
{
 authenticate();
return false;
})


})


function authenticate()
{
    showChat(true);
let userVal=$("#username").val();
let passVal=$("#password").val();
 let data1={
    username:userVal,
    password:passVal
    }
    var dt=JSON.stringify(data1);
    console.log("dt",dt);
    $.ajax({
        url: "https://cgisdev.utcluj.ro/moodle/chat-piu/authenticate.php",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(data1),
        dataType: "json",
        beforeSend: null,
        success: function(response){
            console.log("SUCCCES:",response);
            showChat(true);
           

        },
         error:function(xhr)
         { var message="generic error message"
            if(xhr.responseText)
             message=$.parseJSON(xhr.responseText).message;

             console.log("ERROR:",message);
 
         },
        complete: function(xhr)
        {

        },

        statusCode: {
            401:function()
        {

      }
      }
        })
        
}
function logout()
{


}

function globalLogout()
{


}

function readMsg()
{


}

function sendMsg()
{


}

function showChat(condition)
{
  if(condition)
  {
  $("#chat_form").fadeOut(500,function()
{
    $("welcome").fadeIn(500)
    $("conversation").fadeIn(500)

})



  }else{
    $("welcome").fadeOut(500)
    $("conversation").fadeOut(500,function()
    {
        $("#chat_form").height="1000px";
        $("#chat_form").fadeIn(500)

    })

  }

}

