/**
 * 
 */

const searchUsers = document.getElementById("searchUsers");
const filterField = document.getElementById("filter-field");
const filterType = document.getElementById("filter-type");

let timeOutId;
searchUsers.addEventListener('input',(e)=>{
	clearTimeout(timeOutId);
	console.log(filterField.value);
	if(filterField.value){
		timeOutId = setTimeout(()=>{
		
		console.log(e.target.value);
	table.setFilter(filterField.value,filterType.value,e.target.value);
	},600);	
	}
	
	
})

 
 var table = new Tabulator("#users", {
    pagination:true, //enable pagination
    paginationMode:"remote", //enable remote pagination
    ajaxURL:"/admin/users", //set url for ajax request
    ajaxParams:{token:"ABC123"}, //set any standard parameters to pass with the request
    paginationSize:2, //optional parameter to request a certain number of rows per page
    paginationInitialPage:2, //optional parameter to set the initial page to load
     ajaxConfig:"POST",
     layout: "fitColumns",
     
     columns: [
  { title: "Id", field: "id" },
  { title: "FirstName", field: "FirstName" },
  { title: "LastName", field: "LastName" },
  { title: "Email", field: "Email" },
  { title: "Active", field: "Active" },
  { title: "Deleted", field: "Deleted" },
  { title: "Role", field: "Role" }
  
  // ... more columns
]
});
 