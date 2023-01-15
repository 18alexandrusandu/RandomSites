
var Favorites=[]
var activeCategory="all"




$("document").ready(function()
{

let ac=document.getElementById(activeCategory)
if(ac)
ac.classList.add("active");

console.log(window.getComputedStyle( document.body ,null).getPropertyValue('background-color'))


$("#Cancel").click(function()
{
$("#pop-up").hide();
})
$("#confirm").click(function()
{
$("#pop-up").hide();
})

$("#Cancel2").click(function()
{
$("#pop-up2").hide();
})
$("#confirm2").click(function()
{
$("#pop-up2").hide();
delete_jokes();
Favorites=[];
})

$("#clear").hide();

$("#pop-up").hide();


$("#pop-up2").hide();

$("#waiting").hide();

get_jokes();

$("#all").click(function()
{

delete_jokes();
get_jokes("Any");
let doc=document.getElementById(activeCategory);
if(doc)
doc.classList.remove("active")

let doc2=document.getElementById("all");
if(doc2)
doc2.classList.add("active")
activeCategory="all"

$("#clear").hide();

})

$("#christmas").click(function()
{

delete_jokes();
get_jokes("Christmas");
$("#clear").hide();
let doc=document.getElementById(activeCategory);
if(doc)
doc.classList.remove("active")

let doc2=document.getElementById("christmas");
if(doc2)
doc2.classList.add("active")
activeCategory="christmas"
})


$("#prog").click(function()
{

delete_jokes();
get_jokes("Programming");
let doc=document.getElementById(activeCategory);
if(doc)
doc.classList.remove("active")

let doc2=document.getElementById("prog");
if(doc2)
doc2.classList.add("active")
activeCategory="prog"
$("#clear").hide();
})

$("#misc").click(function()
{
delete_jokes();
get_jokes("Misc");
let doc=document.getElementById(activeCategory);
if(doc)
doc.classList.remove("active")

let doc2=document.getElementById("misc");
if(doc2)
doc2.classList.add("active")
activeCategory="misc"
$("#clear").hide();
})

$("#dark").click(function()
{

delete_jokes();
get_jokes("Dark");
let doc=document.getElementById(activeCategory);
if(doc)
doc.classList.remove("active")
let doc2=document.getElementById("dark");
if(doc2)
doc2.classList.add("active")
activeCategory="dark"

$("#clear").hide();
})

$("#pun").click(function()
{

delete_jokes();
get_jokes("Pun");
let doc=document.getElementById(activeCategory);
if(doc)
doc.classList.remove("active")
let doc2=document.getElementById("pun");
if(doc2)
doc2.classList.add("active")
activeCategory="pun"

$("#clear").hide();
})

$("#spok").click(function()
{

delete_jokes();
get_jokes("Spooky");
$("#active").text("Spooky")
let doc=document.getElementById(activeCategory);
if(doc)
doc.classList.remove("active")
let doc2=document.getElementById("spok");
if(doc2)
doc2.classList.add("active")
activeCategory="spok"


$("#clear").hide();
})

$("#fav").click(function()
{

delete_jokes();
fav_jokes();
let doc=document.getElementById(activeCategory);
if(doc)
doc.classList.remove("active")
let doc2=document.getElementById("fav");
if(doc2)
doc2.classList.add("active")
activeCategory="fav"


$("#clear").show();
if(Favorites.length<1)
{
    $("#errors").text("Favorites List is empty");
}else
$("#errors").text("");

})
$("#clear").click(
function()
{

console.log("favorites",Favorites)
$("#pop-up2").show();

});



});



function fav_jokes()
{

build_jokes(Favorites);
}




function delete_jokes()
{

$("#jokes").empty();

}


function build_jokes(jokes)
{
let i=0
console.log(i)
for( let joke of jokes)
{
i++;

$("#jokes").append('<div id='+'"div'+i+'"'+'></div>')

var txt='<p id="id'+i +'" >Id: '+joke["id"]+'</p>'
$('#div'+i).append(txt)

let inds1="star"+i;
if(Favorites.includes(joke))
{



$('#div'+i).append('<i id="'+inds1+'"class="fa fa-solid fa-star"></i>')

$("#"+inds1).click(function()
{
console.log("clicked")
if(Favorites.includes(joke))
{
$("#"+inds1).remove();
Favorites.splice(Favorites.indexOf(joke),1)

}

})
}

if(joke["type"]=="single")
{


var txt='<p class="single" id="'+i+'">'+joke["joke"]+'</p>'
$("#div"+i).append(txt)
}
else
{

var txt='<p class="notsingle"  id="'+i+'">'+joke["setup"]+'</p>'
$("#div"+i).append(txt)



var txtdelivery='<p class="delivery"  id="delivery'+joke["id"]+'">'+joke["delivery"]+'</p>'
$("#div"+i).append(txtdelivery)
$("#delivery"+joke["id"]).hide();

let id="#delivery"+joke["id"]
let ind="#"+i
$("#"+i).hover(function()
{
$(ind).animate(
{backgroundColor:"orange"},100,function(){
$(ind).css("background-color","orange")

}
);

$(id).fadeIn(1000,null);

})
}



var txt2='<button class="report" id="report' +i+'"> Report </buitton>'
$("#div"+i).append(txt2)
let astr0="#report"+i

$("#report"+i).click(function()
{
$(astr).animate(
{backgroundColor:"magenta"},0,
function(){$(astr0).css("background-color","magenta")}).

animate({backgroundColor:"purple"},100,
function(){$(astr0).css("background-color","purple")})

console.log("pop-up");
$("#pop-up").show();
})


var txt3='<button id="Addf'+i+'" >Add to favorites</button>'
$("#div"+i).append(txt3)
let str="#id"+i;

let astr="#Addf"+i;

let inds="star"+i;
$("#Addf"+i).click(function()
{
console.log("favorites",Favorites)
$(astr).animate(
{backgroundColor:"magenta"},0,
function(){$(astr).css("background-color","magenta")}).

animate({backgroundColor:"purple"},100,
function(){$(astr).css("background-color","purple")})


if(!Favorites.includes(joke)){



$('<i id="'+ inds +'" class="fa fa-solid fa-star"></i>').insertAfter(str)

$("#"+inds).click(function()
{
console.log("clicked")
if(Favorites.includes(joke))
{
$("#"+inds).remove();
Favorites.splice(Favorites.indexOf(joke),1)

}

});

Favorites.push(joke);
}

console.log("favorites",Favorites)
})







}

$("button").css("background-color","purple");

}










function get_jokes(type="Any")
{

$("#waiting").show();
   $.ajax({
        url:"https://sv443.net/jokeapi/v2/joke/"+type+"?amount=10",
        method: "GET",
        contentType: "application/json",
        dataType: "json",
        beforeSend: null,
        success: function(response){
            console.log("SUCCCES:",response["jokes"]);
            build_jokes(response["jokes"]);
             $("#waiting").hide();

        },
         error:function(xhr)
         { 
         var message="Unknown error";
            if(xhr.responseText)
          {
             message=$.parseJSON(xhr.responseText).message;
        }

            $("#errors").text(message);
            $("#errors").css("background-color","lighcoral");
            $("#errors").css("color","red");
             console.log("ERROR:",message);
       
 
         },
        complete: function(xhr)
        {
             $("#waiting").hide();
        },

        statusCode: {
            401:function()
        {}
      }
})
        
}