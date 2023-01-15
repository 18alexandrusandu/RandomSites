var  listTasks=[]


function randomColorUnique()
{

  console.log("in random")
for(let t of listTasks)
{
console.log("TASK",t)
const RanHexColor = Math.floor(Math.random()*16777215).toString(16);
t.div.style.backgroundColor="#" + RanHexColor;
t.color="#" + RanHexColor;
   
}

}


function sortTime()
{

listTasks.sort(
function(a,b){ 

if(a.timeLimit==null && b.timeLimit==null)
return -1

if(a.timeLimit==null && b.timeLimit!= null )
 return 1


if(a.timeLimit!=null && b.timeLimit== null )
 return -1


if(a.timeLimit!=null && b.timeLimit!= null )
 {
var da=new Date(a.timeLimit)
var db= new Date(b.timeLimit)
return da-db;
}
}
)
 displayList()
}
let  iconDownload=document.createElement("i")
iconDownload.className="fa fa-download"
iconDownload.style.color="blue"
iconDownload.style.margin="10px 10px 10px"
let  DownloadText=document.createElement("p")

var a = document.createElement("a");

a.download = "tasks.txt";
DownloadText.textContent="Download the tasks under json format:"
a.appendChild(DownloadText)
a.appendChild(iconDownload)


var d1=document.getElementById("create")

d1.appendChild(a)


function upload()
{
console.log("upload")
var vinput=document.getElementById("addFile")
var reader=new FileReader();

   reader.readAsText(vinput.files[0])
   reader.onloadend=function(event)
   {
     var fileText=event.target.result
     var obj=JSON.parse(fileText)
    for (var o of obj)
  {
   add_task(o)
  }


   }



}




setInterval(
function()
{
let now= new Date().getTime()
for(let t of listTasks)
{
if(t.isDone==false && t.timeLimit!=null)
{
var diffTime=now-t.timeLimit.getTime()
if(diffTime>0)
{
t.div.style.color="red"
}
else
if(diffTime>-t.duration*10/100)
{
t.div.style.color="orange"
}
else
if(diffTime>-t.duration*90/100)
{
t.div.style.color="yellow"
}




}


}

}
,1000)



function doFinished(Task)
{

let newdivTask=document.createElement("div")
let newtextTask=document.createElement("p")
let  newPriorityTask=document.createElement("p")
newdivTask.style.display="flex"

if(Task.color)
newdivTask.style.backgroundColor=Task.color



let  iconUpPriority=document.createElement("i")
iconUpPriority.className="fa fa-solid fa-check"
iconUpPriority.style.color="green"
iconUpPriority.style.margin="10px 10px 10px"

newPriorityTask.textContent="Priority "+Task.priority+":"
newtextTask.textContent=Task.text
newdivTask.appendChild(newPriorityTask)
newdivTask.appendChild(newtextTask)
newdivTask.appendChild(iconUpPriority)



Task.div=newdivTask

Task.isDone=true


}












function displayList()
{
console.log("display")
var taskDiv=document.getElementById("tasks")
var taskDonesDiv=document.getElementById("dones")

taskDiv.innerHtml=""
taskDonesDiv.innerHtml=""
console.log(listTasks)
var countTasks=0
var countDones=0
var cTDiv=document.getElementById("countTasks")
var cDDiv=document.getElementById("countDone")

for( let  t of listTasks)
{
if(t.isDone==false)
{
taskDiv.appendChild(t.div)
countTasks+=1
}

}

for( let t2 of listTasks)
{

if(t2.isDone==true)
{
taskDonesDiv.appendChild(t2.div)
countDones+=1
}

}
cTDiv.textContent="Nr. tasks to do:"+countTasks
cDDiv.textContent="Nr. tasks done:"+countDones


a.href = window.URL.createObjectURL(new Blob([
JSON.stringify(listTasks.map(function(value){ return [value.text,value.isDone,value.timeLimit] }))], {type: "text/plain"}));





}





function add_task(initTask=null)
{



var ta=document.getElementById("text")
var prio=listTasks.length
var txt=ta.value
var doneInit=false
var limitT=null
if(initTask!=null)
{

 txt=initTask[0]
 doneInit=initTask[1]
 limitT=initTask[2]
if(doneInit==true)
{
let tsk={text:initTask[0],priority:0,div:null,timeLimit:Date(limitT),isDone:true,duration:null}
doFinished(tsk)
listTasks.push(tsk)
randomColorUnique()
return ;

}

}



var tasks=document.getElementById("tasks")
var divTask=document.createElement("div")
var textTask=document.createElement("p")
var textTaskPriority=document.createElement("p")

var buttonTask=document.createElement("button")
var limitTime=document.createElement("p")

divTask.style.display="flex"
buttonTask.innerText="Finish Task"
buttonTask.style.width="50px"
buttonTask.style.height="20px"



var super_Task_div=document.createElement("div")


var Task={
div: super_Task_div,
isDone:doneInit,
text:txt,
priority:prio,
timeLimit:null,
duration:null,
color:null
}
 textTaskPriority.textContent="Priority "+Task.priority+":"
var br=document.createElement("br")

buttonTask.addEventListener("click",()=>
{
tasks.removeChild(Task.div)
doFinished(Task)
displayList()


}

)



let  iconUpPriority=document.createElement("i")
iconUpPriority.className="fa fa-arrow-up" 
iconUpPriority.style.color="green"
iconUpPriority.style.margin="10px 10px 10px"
iconUpPriority.addEventListener("click",()=>
{
Task.priority-=1
listTasks.sort(
function(a,b){ return a.priority-b.priority})
displayList()
}
)


textTask.textContent=Task.text

divTask.appendChild( textTaskPriority)
divTask.appendChild( br)
divTask.appendChild(textTask)
divTask.appendChild(iconUpPriority)
divTask.appendChild(buttonTask)





super_Task_div.appendChild(divTask)
super_Task_div.style.border="solid black 3px"
super_Task_div.style.width="max-content"
var dt=document.getElementById("time_limit")
if(dt.value!="")
{
var timeLimitP=document.createElement("p")

console.log("seted a time limit",dt.value)
Task.timeLimit=new Date(dt.value)
timeLimitP.textContent=dt.value
super_Task_div.appendChild(timeLimitP)

let now=new Date()
Task.duration=(Task.timeLimit.getTime()-now.getTime())


}
if(limitT!=null)
{
var timeLimitP=document.createElement("p")

console.log("seted a time limit",limitT)
Task.timeLimit=new Date(limitT)
console.log("seted a time limit dt",Task.timeLimit)
timeLimitP.textContent=limitT
super_Task_div.appendChild(timeLimitP)

let now=new Date()
if(Task.timeLimit.getTime)
Task.duration=(Task.timeLimit.getTime()-now.getTime())



}



listTasks.push(Task)
randomColorUnique()

//do list display lists

displayList()



console.log(listTasks)

}