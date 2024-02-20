/**
 * 
 */
 


 console.log("Hii from base");
 const toggleBtn = document.getElementById("toggleBtn");
 const  toggleMenue= document.getElementById("toggleMenue");
 
 toggleBtn.addEventListener("click",()=>{
	 toggleMenue.classList.toggle("isOpen");
 })

const sortBy = document.getElementById("sortBy");
const dropDownMenu = document.getElementById("dropdown-menu");

sortBy.addEventListener('click',(e)=>{
		if(dropDownMenu.style.display!=="block")
			dropDownMenu.style.display="block";
		else
			dropDownMenu.style.display="none"
});



let searchCont = document.getElementById("searchCont");
if(window.location.href!=="http://localhost:8080/v1/"){
	searchCont.style.display='none';
}else{
	searchCont.style.display='flex';
}
 
 let search= document.getElementById("search");
 let timeOut;
 search.addEventListener("input",(e)=>{
	 const cardsId = document.getElementById("cards");
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
 
 
 
 
/* let pageNo=0;
 
 getAllMovies(pageNo);
 let moviesSize = Number( document.getElementById("spanMovie").innerText.trim());
 console.log("afaef",moviesSize/10,pageNo)
 
 
 
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

 */
 
 
 