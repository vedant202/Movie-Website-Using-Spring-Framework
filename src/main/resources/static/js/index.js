/**
 * 
 */

 console.log("Hii from index");
 
 
 
let pageLength = 0; 
 
 
 function getAllMovies(pageNo=0){
	 console.log("getAllMovies ",pageNo);
	 const cardsId1 = document.getElementById("cards");
 let cardTitle1 = ``;
 let cardOverview1 = ``;
 let temp="";
	 fetch("/v1/movies?page="+pageNo).then((data)=>data.json()).then(jData=>{
	 console.log(jData);
	 
	 if(jData.length>0){
		 
		 temp+=jData.map((i)=>{
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
		 cardsId1.innerHTML = temp;
	 }else{
		 cardsId1.innerHTML=`<h1>Nothing to Display</h1>`;
	 }
 }).catch((e)=>{
	 console.log(e);
	 cardsId1.innerHTML=`<h1>Nothing to Display</h1>`;
 })
 }
 
 let pageNo=0;
 
 let moviesSize = Number( document.getElementById("spanMovie").innerText.trim());
 console.log("afaef",moviesSize/10,pageNo)
 getAllMovies(pageNo);
 
 
 
	  let next = document.getElementById("next");
 next.addEventListener("click",(e)=>{
	 pageNo = pageNo+1<moviesSize/10?pageNo+1:pageNo;
	 
	 console.log("Next clicked ",pageNo);
	 if(pageNo<moviesSize/10){
		 getAllMovies(pageNo);
	 }
	 
 })
 
  let prev = document.getElementById("prev");
 prev.addEventListener("click",(e)=>{
	 pageNo = pageNo-1>=0?pageNo-1:pageNo;
	
	 console.log("Next clicked ",pageNo);
	 if(pageNo>=0){
		  getAllMovies(pageNo);
	 }
	
 })

 
 
 
 
