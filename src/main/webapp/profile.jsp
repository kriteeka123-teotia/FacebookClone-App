<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Profile</h2>
    <div class="mt-3">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Username: JohnDoe</h5>
                <p class="card-text">Email: johndoe@example.com</p>
                <p class="card-text">About Me: This is an example profile.</p>
            </div>
        </div>
    </div>
    <div class="mt-3 text-center">
        <a href="feed.jsp" class="btn btn-secondary">Back to Feed</a>
        <a href="logout" class="btn btn-danger">Logout</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
