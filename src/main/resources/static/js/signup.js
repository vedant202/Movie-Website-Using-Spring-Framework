/**
 * 
 */

 let confirmPassword = document.getElementById("cpassword");
 let password = document.getElementById("password");
 let error = document.getElementById("error");
 function checkPassword(){
	 console.log(password.value);
	 console.log(confirmPassword.value);
	 console.log(password.value===confirmPassword.value)
	 if(!(password.value===confirmPassword.value)){
		 error.innerText="Invalid credentials, check passwords";
	 }
	 return password.value===confirmPassword.value;
	 
 }
 