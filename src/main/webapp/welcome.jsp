<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Facebook Clone</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <!-- Logout link -->
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="text-center">Welcome, <%= session.getAttribute("username") %>!</h1>
        <p class="text-center">Here is your profile information:</p>

        <div class="card mx-auto" style="width: 50%;">
            <div class="card-body">
                <p><strong>Full Name:</strong> <%= session.getAttribute("username") %></p>
                <p><strong>Email:</strong> <%= session.getAttribute("email") %></p>
                <p><strong>Bio:</strong> <%= session.getAttribute("bio") %></p>
                <a href="edit.jsp" class="btn btn-primary">Edit Profile</a>
                <a href="profile.jsp" class="btn btn-primary">See Profile</a>
                <a href="feed.jsp" class="btn btn-primary">Feed</a>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
