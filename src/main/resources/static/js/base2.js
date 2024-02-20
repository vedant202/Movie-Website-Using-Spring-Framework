/**
 * 
 */

 
 async function  handleRadioChange(e){
	 
	 const cardsId = document.getElementById("cards");
	 let cardTitle = ``;
	 let cardOverview = ``;

	 
	console.log("Radio changed ",e.value);
	let pageNo=0;
	let url ="";
	let method = "GET";
	if(e.value.trim()=="sortByPopularity"){
		 url = "/v1/movie/sort/popularity?page="+pageNo;
		 method = "POST";
	}
	/*sortByVoteCount*/
	else if(e.value.trim()=="sortByVoteCount"){
		url = "/v1/movie/sort/VoteCount?page="+pageNo;
		 method = "POST";
	}
	else{
		url = "/v1/movies?page="+pageNo;
		method = "GET";
		
	}
	 let data= await fetch(url,{
		method:method
	});
	
	let movies = await data.json();
	
	cardsId.innerHTM=``;
				let cardsInnerHTML="";
		 if(movies.length>0){
			 
				 cardsInnerHTML+=movies.map((i)=>{
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
	
	
	
}

