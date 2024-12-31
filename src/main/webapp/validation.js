function loginValidation()
{
	
const username=document.getElementById("username").value;
const password=document.getElementById("password").value;

	if(username==="")
	{
		alert('Invalid username!!');
	}
	if(password==="")
		{
			alert('Invalid password!!');
		}
}


function registerValidation()
{
	
const username=document.getElementById("username").value;
const email=document.getElementById("email").value;
const password=document.getElementById("password").value;

	if(username==="")
	{
		alert('Invalid username!!');
	}
	if(email==="" || !email.includes("@"))
		{
			alert('Invalid email!!');
		}
	if(password==="")
		{
			alert('Invalid password!!');
		}
}
