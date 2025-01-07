package com.facebookclone.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.facebookclone.dao.UserDAO;
import com.facebookclone.model.User;

@WebServlet("/")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public Home() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/":
                    requestDispatcher = request.getRequestDispatcher("index.jsp");
                    requestDispatcher.forward(request, response);
                    break;

                case "/register":
                    requestDispatcher = request.getRequestDispatcher("register.jsp");
                    requestDispatcher.forward(request, response);
                    break;

                case "/login":
                    requestDispatcher = request.getRequestDispatcher("login.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                    
                case "/logout":
                    handleLogout(request, response);
                    break;

                case "/profile":
                    requestDispatcher = request.getRequestDispatcher("profile.jsp");
                    requestDispatcher.forward(request, response);
                    break;

                case "/feed":
                    requestDispatcher = request.getRequestDispatcher("feed.jsp");
                    requestDispatcher.forward(request, response);
                    break;

                case "/welcome":
                    requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                    requestDispatcher.forward(request, response);
                    break;

                case "/edit":
                    requestDispatcher = request.getRequestDispatcher("edit.jsp");
                    requestDispatcher.forward(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();
        
    	if ("/LoginServlet".equals(action)) {
            try {
				handleLogin(request, response);
			} catch (ClassNotFoundException | ServletException | IOException e) {
				return;
			}
        } else if ("/RegisterServlet".equals(action)) {
            try {
				handleRegister(request, response);
			} catch (ClassNotFoundException | ServletException | IOException e) {
				return;
			}
        } else if ("/EditProfileServlet".equals(action)) {
            try {
				handleEdit(request, response);
			} catch (ClassNotFoundException | ServletException | IOException e) {
				return;
			}
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
    	String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO dao = new UserDAO();

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        try {
            dao.registerUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("bio", user.getBio());
            session.setAttribute("fullname", user.getFullname());
            response.sendRedirect("welcome.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        }
    }


    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO dao = new UserDAO();

        try {
            User user = dao.loginUser(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("fullname", user.getFullname());
                session.setAttribute("email", user.getEmail());
                response.sendRedirect("welcome.jsp"); // Redirect to welcome page
            } else {
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        }
    }
    
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
    
    private void handleEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
    	// Get updated information from the request
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String bio = request.getParameter("bio");
        
    	HttpSession session = request.getSession(); // Get the existing session
        if (session == null || session.getAttribute("userId") == null) {
            // If no session or userId attribute is found, redirect to login
            response.sendRedirect("login.jsp");
            return;
        }

        // If userId is present, continue with profile editing
        Integer userId = (Integer) session.getAttribute("userId"); // Get the logged-in user's ID

        // Create a User object and set its fields
        User user = new User();
        user.setId(userId); // Set the user ID to identify which user to update
        user.setFullname(fullname);
        user.setEmail(email);
        user.setBio(bio);

        // Create an instance of the UserDAO to interact with the database
        UserDAO userDAO = new UserDAO();

        // Update the user information in the database
        boolean isUpdated = userDAO.updateUser(user);

        if (isUpdated) {
            // If update is successful, redirect to profile page
            response.sendRedirect("profile.jsp");
        } else {
            // If update failed, show an error message
            request.setAttribute("errorMessage", "Failed to update profile. Please try again.");
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }
        
    }
}
