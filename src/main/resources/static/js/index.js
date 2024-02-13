/**
 * 
 */

 console.log("Hii from index");
 
 const toggleBtn = document.getElementById("toggleBtn");
 const  toggleMenue= document.getElementById("toggleMenue");
 
 toggleBtn.addEventListener("click",()=>{
	 toggleMenue.classList.toggle("isOpen");
 })
 
 fetch("/movies").then((data)=>data.json()).then(jData=>{
	 console.log(jData)
 })
 