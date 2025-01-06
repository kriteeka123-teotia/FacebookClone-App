<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script type="text/javascript" lang="javascript">
	
<%@include file="validation.js" %>
	
</script>
</head>
<body>
	<div class="container mt-5">
		<h2 class="text-center">Login</h2>
		<form action="LoginServlet" method="post" class="mt-3">
			<div class="mb-3">
				<label for="username" class="form-label">Username</label> <input
					type="text" class="form-control" id="username" name="username"
					required>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					type="password" class="form-control" id="password" name="password"
					required>
			</div>
			<button type="submit" class="btn btn-primary w-100"
				onclick="loginValidation()">Login</button>
			<p class="mt-3 text-center">
				Don't have an account? <a href="register.jsp">Register here</a>
			</p>
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
