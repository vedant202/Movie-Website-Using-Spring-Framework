/**
 * 
 */

console.log("Hii from index");



let pageLength = 0;
let pageNo = 0;
let url = "/v1/movies?page=";


function handleLikes(id, count,element) {
	fetch("/v1/movie/Setlike", {
		method: "POST",
		headers: {
			'Content-type': "application/json",
		},
		body: JSON.stringify({ id: id, count: count })
	}).then((data)=>data.json()).
	then((counts)=>{
		console.log("fetch liked counts", counts);
		element.innerText=counts;
	})
	.catch(error=>{
		console.log(error)
	})
}

async function getAllMovies(url, pageNo = 0, method = "GET") {
	console.log("getAllMovies ", pageNo, " url:- ", url);
	const cardsId1 = document.getElementById("cards");
	let cardTitle1 = ``;
	let cardOverview1 = ``;
	let temp = "";
	let movieId = "";

	await fetch(url + pageNo, {
		method: method
	}).then((data) => data.json()).then(jData => {
		console.log(jData);

		if (jData.length > 0) {


			temp += jData.map((i) => {

				cardTitle1 = i['title'];
				cardOverview1 = i['overview'];
				let imageVar = `<img id="like" src="/images/like.png"/>`;
				if(i.userLiked){
					imageVar = `<img id="like" src="/images/like 2.png"/>`
				}
				let card = `<div class="card">
				
					<div class="top"> ${cardTitle1} </div>
					<div class="mid">
					${cardOverview1}
					</div>
					<div class="wrapper">
					<div class="foot">Average Votes :- ${i['vote_average']}</div>
					<div class="likes">
						${imageVar}
						<input type="hidden" id="input${i['id']}" value="${i['id']}"></input>
						<div class="count" id="count${i['id']}">${i['likeCount']}</div>
					</div>
					</div>
				</div>`;

				return card;
			})
			cardsId1.innerHTML = temp;

		} else {
			cardsId1.innerHTML = `<h1>Nothing to Display</h1>`;
		}

	}).catch((e) => {
		console.log(e);
		cardsId1.innerHTML = `<h1>Nothing to Display</h1>`;
	})

	like = document.querySelectorAll("#like");
	console.log("checking like after data fetched ", like);
	like.forEach(i => i.addEventListener('click', (e) => {
		movieId = i.nextElementSibling.value;
		console.log("movieId ",movieId)
		console.log("clicked ", like.src);
		if (!i.src.includes("2")) {
			console.log("In if")
			i.src = "/images/like 2.png";
/*			document.getElementById("count"+movieId).innerText = Number(document.getElementById("count"+movieId).innerText) + 1;
*/			handleLikes(movieId, Number(document.getElementById("count"+movieId).innerText),document.getElementById("count"+movieId));
			
		} else {
			i.src = "/images/like.png";
/*			Number(document.getElementById("count"+movieId).innerText) > 0 ? document.getElementById("count"+movieId).innerText = Number(document.getElementById("count"+movieId).innerText) - 1 : document.getElementById("count"+movieId).innerText = document.getElementById("count"+movieId).innerText;
*/			handleLikes(movieId, Number(document.getElementById("count"+movieId).innerText),document.getElementById("count"+movieId));
			}



	}))
}




getAllMovies(url, pageNo);

let radioCheck = "";

function handleRadioChange(e) {
	console.log("Radio changed ", e.value);

	let url = "";
	let method = "GET";
	if (e.value.trim() == "sortByPopularity") {
		radioCheck = "/v1/movie/sort/popularity?page=";
		url = "/v1/movie/sort/popularity?page=";
		method = "POST";
	}
	/*sortByVoteCount*/
	else if (e.value.trim() == "sortByVoteCount") {
		radioCheck = "/v1/movie/sort/VoteCount?page=";
		url = "/v1/movie/sort/VoteCount?page=";
		method = "POST";
	}
	else {
		radioCheck = "/v1/movies?page=";
		url = "/v1/movies?page=";
		method = "GET";

	}
	console.log("Radio Checked :- ", radioCheck)
	getAllMovies(url, pageNo, method)



}



let moviesSize = Number(document.getElementById("spanMovie").innerText.trim());





let next = document.getElementById("next");
next.addEventListener("click", (e) => {
	console.log("Radio Checked :- ", radioCheck, pageNo)
	if (radioCheck != "" && (radioCheck.toLocaleLowerCase().includes("popularity") || radioCheck.toLocaleLowerCase().includes("votecount"))) {
		method = "POST"
		url = radioCheck;
		console.log("Checking url :- " + url);
		pageNo = pageNo + 1 < moviesSize / 10 ? pageNo + 1 : pageNo;

		console.log("Next clicked ", pageNo);
		if (pageNo < moviesSize / 10) {

			getAllMovies(url, pageNo, method);
		}
	} else {
		console.log("Checking url in else :- " + url);
		pageNo = pageNo + 1 < moviesSize / 10 ? pageNo + 1 : pageNo;
		url = radioCheck === "" ? url : radioCheck;
		console.log("Next clicked ", pageNo);
		if (pageNo < moviesSize / 10) {

			getAllMovies(url, pageNo);
		}
	}


})

let prev = document.getElementById("prev");
prev.addEventListener("click", (e) => {

	if (radioCheck != "" && (radioCheck.toLocaleLowerCase().includes("popularity") || radioCheck.toLocaleLowerCase().includes("votecount"))) {
		method = "POST"
		url = radioCheck;



		pageNo = pageNo - 1 >= 0 ? pageNo - 1 : pageNo;

		console.log("Next clicked ", pageNo);


		if (pageNo >= 0) {
			getAllMovies(url, pageNo, method);
		}
	} else {
		pageNo = pageNo - 1 >= 0 ? pageNo - 1 : pageNo;

		console.log("Next clicked ", pageNo);
		url = radioCheck === "" ? url : radioCheck;


		if (pageNo >= 0) {
			getAllMovies(url, pageNo);
		}
	}
})


let like = document.querySelectorAll("like");
console.log("liked", like);
/*const countEle = document.getElementById("count");
let count = countEle.value;
*/


