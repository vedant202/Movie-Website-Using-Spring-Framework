/**
 * 
 */

 console.log("Hii from index");
 
 const toggleBtn = document.getElementById("toggleBtn");
 const  toggleMenue= document.getElementById("toggleMenue");
 
 toggleBtn.addEventListener("click",()=>{
	 toggleMenue.classList.toggle("isOpen");
 })
 
 
 const cardsId = document.getElementById("cards");
 let cardTitle = ``;
 let cardOverview = ``;
 
 
 fetch("/movies").then((data)=>data.json()).then(jData=>{
	 console.log(jData);
	 if(jData.length>0){
		 cardsId.innerHTML+=jData.map((i)=>{
			 cardTitle=i['title'];
			 cardOverview=i['overview'];
			 
			 let card = `<div class="card">
					<div class="top"> ${cardTitle} </div>
					<div class="mid">
					${cardOverview.length>100?cardOverview.slice(0,100)+'...':cardOverview}
					</div>
					<div class="foot">Average Votes :- ${i['vote_average']}</div>
					
				</div>`;
			 
			 return card;
		 })
	 }else{
		 cardsId.innerHTML=`<h1>Nothing to Display</h1>`;
	 }
 }).catch((e)=>{
	 console.log(e);
	 cardsId.innerHTML=`<h1>Nothing to Display</h1>`;
 })
 
 