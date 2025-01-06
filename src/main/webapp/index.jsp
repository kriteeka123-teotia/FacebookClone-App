<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Facebook Clone</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f0f2f5;
        }
        .container {
            margin-top: 150px;
            text-align: center;
        }
        .logo {
            font-family: Arial, sans-serif;
            font-size: 48px;
            color: #1877f2;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .btn-custom {
            width: 150px;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="logo">Facebook</div>
        <p class="lead">Connect with friends and the world around you.</p>
        <div class="mt-4">
            <a href="login.jsp" class="btn btn-primary btn-custom">Login</a>
            <a href="register.jsp" class="btn btn-secondary btn-custom">Register</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
