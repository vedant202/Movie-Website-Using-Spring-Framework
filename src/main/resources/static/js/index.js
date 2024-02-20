/**
 * 
 */

console.log("Hii from index");



let pageLength = 0;
let pageNo = 0;
let url = "/v1/movies?page=";

function getAllMovies(url, pageNo = 0, method = "GET") {
	console.log("getAllMovies ", pageNo, " url:- ", url);
	const cardsId1 = document.getElementById("cards");
	let cardTitle1 = ``;
	let cardOverview1 = ``;
	let temp = "";
	fetch(url + pageNo, {
		method: method
	}).then((data) => data.json()).then(jData => {
		console.log(jData);

		if (jData.length > 0) {

			temp += jData.map((i) => {
				cardTitle1 = i['title'];
				cardOverview1 = i['overview'];

				let card = `<div class="card">
					<div class="top"> ${cardTitle1} </div>
					<div class="mid">
					${cardOverview1}
					</div>
					<div class="foot">Average Votes :- ${i['vote_average']}</div>
					
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
		url = radioCheck===""?url:radioCheck;
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
		url = radioCheck===""?url:radioCheck;


		if (pageNo >= 0) {
			getAllMovies(url, pageNo);
		}
	}
})





