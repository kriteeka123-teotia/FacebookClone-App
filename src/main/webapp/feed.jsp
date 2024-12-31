<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Feed</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Your Feed</h2>
    <div class="mt-3">
        <!-- Example post -->
        <div class="card mb-3">
            <div class="card-header">Posted by: User1</div>
            <div class="card-body">
                <p class="card-text">This is an example post on the feed.</p>
            </div>
        </div>
        <!-- Add more posts dynamically -->
    </div>
    <div class="mt-3 text-center">
        <a href="profile.jsp" class="btn btn-secondary">View Profile</a>
        <a href="logout" class="btn btn-danger">Logout</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
