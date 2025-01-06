<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Edit Your Profile</h1>
        <form action="EditProfileServlet" method="post">
            <div class="form-group">
                <label for="fullname">Full Name:</label>
                <input type="text" name="fullname" class="form-control" value="<%= session.getAttribute("fullname") %>" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" name="email" class="form-control" value="<%= session.getAttribute("email") %>" required>
            </div>
            <div class="form-group">
                <label for="bio">Bio:</label>
                <textarea name="bio" class="form-control" required><%= session.getAttribute("bio") %></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
