/**
 * 
 */

 console.log("Hii from index");
 
 const cardsId1 = document.getElementById("cards");
 let cardTitle1 = ``;
 let cardOverview1 = ``;
 
 
 fetch("/movies").then((data)=>data.json()).then(jData=>{
	 console.log(jData);
	 if(jData.length>0){
		 cardsId1.innerHTML+=jData.map((i)=>{
			 cardTitle1=i['title'];
			 cardOverview1=i['overview'];
			 
			 let card = `<div class="card">
					<div class="top"> ${cardTitle1} </div>
					<div class="mid">
					${cardOverview1.length>100?cardOverview1.slice(0,100)+'...':cardOverview1}
					</div>
					<div class="foot">Average Votes :- ${i['vote_average']}</div>
					
				</div>`;
			 
			 return card;
		 })
	 }else{
		 cardsId1.innerHTML=`<h1>Nothing to Display</h1>`;
	 }
 }).catch((e)=>{
	 console.log(e);
	 cardsId1.innerHTML=`<h1>Nothing to Display</h1>`;
 })
 
