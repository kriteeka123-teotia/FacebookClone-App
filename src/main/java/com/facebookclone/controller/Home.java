package com.facebookclone.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.facebookclone.dao.UserDAO;
import com.facebookclone.model.User;

@WebServlet("/")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO dao;

    public Home() {
        super();
    }

    public void init() {
        dao = new UserDAO();
    }

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

                case "/RegisterServlet":
                    handleRegister(request, response);
                    break;

                case "/login":
                    requestDispatcher = request.getRequestDispatcher("login.jsp");
                    requestDispatcher.forward(request, response);
                    break;

                case "/LoginServlet":
                    handleLogin(request, response);
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
                    
                case "/EditProfileServlet":
                    handleEdit(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        dao.registerUser(user);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
        requestDispatcher.forward(request, response);
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = dao.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE name = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("status", "active");
                session.setAttribute("username", username);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
                requestDispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("status", "inactive");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
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
    
    private void handleEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        dao.updateUser(user);

        session.setAttribute("username", username);
        session.setAttribute("email", email);
        session.setAttribute("password", password);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
        requestDispatcher.forward(request, response);
    }

}
