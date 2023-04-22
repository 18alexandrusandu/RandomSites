window.onload=function()
{

let formRef=document.getElementsByClassName("contact_form")[0];

reset();


document.getElementById("reset").addEventListener("click",function(){
    reset();
})
document.getElementById("trimite").addEventListener("click",function(){

 let vld=true;
 errorRef=document.getElementById("errors_list").innerHTML="";
 console.log( "brfore",errorRef)
vld=(vld && validateLastName());
vld=(validateFirstName() && vld );
vld=( validateAddress() && vld);
vld=( validateBirthDate() && vld);
vld=(validatePhone() && vld);
vld=(validateEmail() && vld);

console.log( "after",errorRef)


document.getElementById("errors").style.display= "block";
details=document.getElementById("error_details");

if(vld==true)
details.innerHTML="Comfirmare:Form Valid"
else
details.innerHTML="Please correct the followiung errors and try again"




})

    document.getElementById("lname").onblur=validateLastName;
    document.getElementById("name").onblur=validateFirstName;
    document.getElementById("ad").onblur=validateAddress;
    document.getElementById("dn").onblur=validateBirthDate;
    document.getElementById("te").onblur=validatePhone;
    document.getElementById("eml").onblur=validateEmail;


}



function reset()
{
    document.getElementById("errors").style.display="none";
    document.getElementById("name").classList.remove("valid")
    document.getElementById("name").classList.remove("invalid")
    document.getElementById("lname").classList.remove("invalid")
    document.getElementById("lname").classList.remove("valid")
    document.getElementById("ad").classList.remove("invalid")
    document.getElementById("ad").classList.remove("valid")
    document.getElementById("dn").classList.remove("invalid")
    document.getElementById("dn").classList.remove("valid")
    document.getElementById("te").classList.remove("invalid")
    document.getElementById("te").classList.remove("valid")
    document.getElementById("eml").classList.remove("invalid")
    document.getElementById("eml").classList.remove("valid")
    
    document.getElementById("errors").innerHtml="";
    document.getElementById("error_details").innerHtml="";
    
    document.getElementById("name").value=""
    document.getElementById("lname").value=""
    document.getElementById("ad").value=""
    document.getElementById("dn").value=""
    document.getElementById("te").value=""
    document.getElementById("eml").value=""
    
    
}





function validateInputs()
{

    return true;
}
function validateLastName(){
    elemRef=document.getElementById("lname")
    let value=elemRef.value;
    let reg= /^[a-zA-Z]{3,}$/

    if(value.length>0 && reg.test(value))
    {
     elemRef.classList.add("valid")
     elemRef.classList.remove("invalid")

          return true;
    }else
    {
       
        elemRef.classList.add("invalid")
        elemRef.classList.remove("valid")

        errorRef=document.getElementById("errors_list");
        var li = document.createElement("li");
        label=elemRef.labels[0];
        li.innerHTML=label.innerHTML+",";
        errorRef.appendChild(li);



        return false;
    }
    
}
function validateFirstName(){
    elemRef=document.getElementById("name")
    let value=elemRef.value;
    let reg= /^[a-zA-Z]{3,}$/

    if(value.length>0 && reg.test(value))
    {
        
     elemRef.classList.add("valid")
     elemRef.classList.remove("invalid")

          return true;
    }else
    {
        console.log("here")
        elemRef.classList.add("invalid")
        elemRef.classList.remove("valid")
        errorRef=document.getElementById("errors_list");
        var li = document.createElement("li");
        label=elemRef.labels[0];
        li.innerHTML=label.innerHTML+",";
        errorRef.appendChild(li);

        return false;
    }
    




}
function validateAddress(){

    elemRef=document.getElementById("ad")
    let value=elemRef.value;
    let reg= /^[^@#$%\^\&\*0-9]{3,}([0-9])+/

    if(value.length>0 && reg.test(value))
    {
     elemRef.classList.add("valid")
     elemRef.classList.remove("invalid")

          return true;
    }else
    {
        elemRef.classList.add("invalid")
        elemRef.classList.remove("valid")
        errorRef=document.getElementById("errors_list");
        var li = document.createElement("li");
        label=elemRef.labels[0];
        li.innerHTML=label.innerHTML+",";
        errorRef.appendChild(li);
        return false;
    }
    



}
function validateBirthDate(){

    elemRef=document.getElementById("dn")
    let value=elemRef.value;
    if(value.length>1)
    {
    elemRef.classList.add("valid")
    elemRef.classList.remove("invalid")

         return true;
   }else
   {
      
       elemRef.classList.add("invalid")
       elemRef.classList.remove("valid")
       errorRef=document.getElementById("errors_list");
       var li = document.createElement("li");
       label=elemRef.labels[0];
       li.innerHTML=label.innerHTML+",";
       errorRef.appendChild(li);

       return false;
   }


}
function validatePhone(){
    elemRef=document.getElementById("te")
    let value=elemRef.value;
    let reg= /^[0-9]{3}-[0-9]{9}/

    if(value.length>0 && reg.test(value))
    {
     elemRef.classList.add("valid")
     elemRef.classList.remove("invalid")

          return true;
    }else
    {
       
        elemRef.classList.add("invalid")
        elemRef.classList.remove("valid")
        errorRef=document.getElementById("errors_list");
        var li = document.createElement("li");
        label=elemRef.labels[0];
        li.innerHTML=label.innerHTML+",";
       

        return false;
    }
    




}
function validateEmail(){

    elemRef=document.getElementById("eml")
    let value=elemRef.value;
    let reg= /(.*)@(.*)\.(.*)/

    if(value.length>0 && reg.test(value))
    {
     elemRef.classList.add("valid")
     elemRef.classList.remove("invalid")

          return true;
    }else
    {
       
        elemRef.classList.add("invalid")
        elemRef.classList.remove("valid")
        errorRef=document.getElementById("errors_list");
        var li = document.createElement("li");
        label=elemRef.labels[0];
        li.innerHTML=label.innerHTML+",";
        errorRef.appendChild(li);
        return false;
    }
    

}