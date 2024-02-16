/**
 * 
 */


 console.log("Hii from base");
 const toggleBtn = document.getElementById("toggleBtn");
 const  toggleMenue= document.getElementById("toggleMenue");
 
 toggleBtn.addEventListener("click",()=>{
	 toggleMenue.classList.toggle("isOpen");
 })

const cardsId = document.getElementById("cards");
 let cardTitle = ``;
 let cardOverview = ``;
 
 let search= document.getElementById("search");
 let timeOut;
 search.addEventListener("input",(e)=>{
	 clearTimeout(timeOut);
	 timeOut= setTimeout(async()=>{
		 console.log(e.target.value);
		 let url = `/v1/movie/search?title=${e.target.value}`;
		 let response=await fetch(url,{
			 method:"POST",
			 
			 
		 })
		 let jsonResp = await response.json();
		 console.log(jsonResp);
		 cardsId.innerHTM=``;
				let cardsInnerHTML="";
		 if(jsonResp.length>0){
				 cardsInnerHTML+=jsonResp.map((i)=>{
					 console.log(i);
					 cardTitle=i['title'];
					 cardOverview=i['overview'];
					 card = `<div class="card">
							<div class="top"> ${cardTitle} </div>
							<div class="mid">
							${cardOverview.length>100?cardOverview.slice(0,100)+'...':cardOverview}
							</div>
							<div class="foot">Average Votes :- ${i['vote_average']}</div>
							
						</div>`;
						
						return card;
				 });
				
		 }else{
			 cardsInnerHTML="<h2>No Results Found</h2>"
		 }
		  
		 document.getElementById("cards").innerHTML = cardsInnerHTML;
		 
	 },2000)
	 
	 
 });
 