package com.yoko.Controller;

import java.io.IOException;

import javax.print.attribute.standard.Sides;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.yoko.dao.DBOperation;

/**
 * @author ren
 * @π‹¿Ì‘±µ«¬º
 */
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		DBOperation dbo = new DBOperation();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:" + username+";password:"+password);
		
		HttpSession session = request.getSession();
		Boolean ret = dbo.login(username, password);
		if (ret) {
			session.setAttribute("login", "true");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("case.jsp");
			dispatcher.forward(request, response);
		} else {
			session.setAttribute("login", "false");
			response.sendRedirect("admin.html");
		/*request.getRequestDispatcher("failure.html").forward(request,
					response);
			*/
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
