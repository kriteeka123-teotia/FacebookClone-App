package com.facebookclone.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Home() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher;
		String action=request.getServletPath();
		
		switch(action)
		{
		case "/register":
			requestDispatcher=request.getRequestDispatcher("register.jsp");
			requestDispatcher.forward(request, response);
			break;
			
		case "/login":
			requestDispatcher=request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
