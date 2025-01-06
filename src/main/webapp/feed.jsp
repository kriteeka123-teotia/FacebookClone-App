<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Feed</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Facebook Clone</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="profile.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="text-center">Welcome to Your Feed</h1>
        <div class="mt-4">
            <!-- Example feed items -->
            <div class="card mb-3">
                <div class="card-header">
                    Posted by <b>John Doe</b> on <i>2024-01-01</i>
                </div>
                <div class="card-body">
                    <p>This is a sample post in the feed.</p>
                </div>
            </div>
            <div class="card mb-3">
                <div class="card-header">
                    Posted by <b>Jane Smith</b> on <i>2024-01-02</i>
                </div>
                <div class="card-body">
                    <p>Another example post for your feed.</p>
                </div>
                <div>
                	<a href="profile.jsp" class="btn btn-primary">See Profile</a>
                	<a href="logout" class="btn btn-primary">Logout</a>
                </div>
            </div>
            <!-- Add more feed items dynamically -->
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
