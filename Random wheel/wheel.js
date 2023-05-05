var c = document.getElementById("can");
var ctx = c.getContext("2d");


ctx.beginPath();
ctx.arc(250, 250, 200, 0, 2 * Math.PI);
ctx.stroke();

var reds=Math.floor(Math.random()*255)
var greens=Math.floor(Math.random()*255)
var blues=Math.floor(Math.random()*255)
ctx.fillStyle = "rgba("+reds+"," +greens+", "+blues+", " + 1 + ")"; 
ctx.fill();
ctx.closePath();


var tasks=[]
var choosenTask;
var timeOfSpinLower=1000
var timeOfSpinUpper=20000
var speed


function draw_triangle()
{
ctx.beginPath();
 ctx.moveTo(230,30);
 ctx.lineTo(265,30);
 ctx.lineTo(248,90);
 ctx.lineTo(230,30);
ctx.fillStyle = "black"
ctx.fill();
ctx.stroke();
ctx.closePath();

}






function pausecomp(millis)
{
    var date = new Date();
    var curDate = null;
    do { curDate = new Date(); }
    while(curDate-date < millis);
}



function build_tasks()
{
var size=0;
for(var i=0;i<size;i++)
{

var red=Math.floor(Math.random()*255)
var green=Math.floor(Math.random()*255)
var blue=Math.floor(Math.random()*255)



tasks.push({"red":red,"green":green,"blue":blue})



}



}


function color_tasks(time)
{
var size=tasks.length;
var per=2.0/size;
console.log("size is",size)

ctx.clearRect(0, 0, c.width, c.height);


for(var i=0;i<size;i++)
{

var red=tasks[i]["red"]
var green=tasks[i]["green"]
var blue=tasks[i]["blue"]


ctx.beginPath();
 ctx.moveTo(250,250);
ctx.arc(250, 250, 200, (i*per +time)*Math.PI, ((i+1)*per+time)*Math.PI);
ctx.stroke()
ctx.fillStyle = "rgba("+red+"," +green+", "+blue+", " + 1 + ")"; 

ctx.fill()
ctx.closePath();





}
draw_triangle()
for(var i=0;i<size;i++)
{
ctx.beginPath();
ctx.font = "30px Arial";

var newx=230+200*Math.cos(((i+1)*per*Math.PI+i*per*Math.PI+2*time*Math.PI)/2)
var newy=255+200*Math.sin(((i+1)*per*Math.PI+i*per*Math.PI+2*time*Math.PI)/2)

 ctx.moveTo(250,250);
if(tasks[i]["text"]==null)
ctx.strokeText("Task"+i, newx,newy);	
else
ctx.strokeText(tasks[i]["text"], newx,newy);
ctx.closePath();
}







}
build_tasks()

function add_task()
{

var red=Math.floor(Math.random()*255)
var green=Math.floor(Math.random()*255)
var blue=Math.floor(Math.random()*255)

var inp=document.getElementById("inp").value;
tasks.push({
"text":inp,
"red":red,
"green":green,
"blue":blue
});
color_tasks(0)


}

function add_many_tasks(evt)
{

console.log(evt)
    let files = evt.target.files; // FileList object

    // use the 1st file from the list
    let f = files[0];

    
    let reader = new FileReader();
reader.onload = event => {console.log(event.target.result);

var data=event.target.result
 var obj=JSON.parse(data)
for(var d of obj)
{

var red=Math.floor(Math.random()*255)
var green=Math.floor(Math.random()*255)
var blue=Math.floor(Math.random()*255)


tasks.push({
"text":d[0],
"red":red,
"green":green,
"blue":blue
});

}
color_tasks(0)

 // desired file content

}
reader.onerror = error => reject(error)
      reader.readAsText(f);





  }



 document.getElementById('many').addEventListener('change', add_many_tasks, false);





var t=0

function areClockwise(v1x, v1y,v2x,v2y) {
  return -v1x*v2y + v1y*v2x > 0;
}




function findWinner()
{
var time=t*0.05
var right_angle=1.5*Math.PI
var per=2.0/tasks.length;

for(var i=0;i<tasks.length;i++)
{

var angle_start=(i*per +time)*Math.PI
var angle_stop =((i+1)*per+time)*Math.PI

if(!areClockwise(Math.cos(angle_start), Math.sin(angle_start) ,Math.cos( right_angle),Math.sin( right_angle)) && areClockwise(Math.cos(angle_stop),Math.sin(angle_stop),Math.cos( right_angle),Math.sin( right_angle)))
return i


}

spinWheel()




}



function spinWheel()
{
var seted_time=timeOfSpinLower+Math.random()*(timeOfSpinUpper-timeOfSpinLower)
var start_time=new Date()
var intid=setInterval(function () {
var cur_time=new Date();
console.log(seted_time)
console.log(cur_time-start_time)
if(cur_time-start_time<seted_time)
{
t+=10;color_tasks(t*0.05)
}
else
{
color_tasks(t*0.05)
clearInterval(intid);
var winnerIndex=findWinner()
console.log( "Winner is:",winnerIndex)

var par=document.getElementById("result");
if(tasks[winnerIndex]["text"])
{
par.textContent ="The chosen choice is:"+tasks[winnerIndex]["text"]

}
else
{

par.textContent ="The chosen choice is:"+"Task"+winnerIndex




}
startConfetti()
}
},200)



}

//color_tasks(0)












