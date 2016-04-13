package com.yoko.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yoko.dao.DB;
import com.yoko.dao.DBOperation;

/**
 * Servlet implementation class UploadMessage
 */
@WebServlet("/UploadMessage")
public class UploadMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadMessage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("name");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");
		System.out.println(username);
		System.out.println(phone);
		System.out.println(message);
		try {
			DBOperation operation = new DBOperation();
			operation.addMessage(username, phone, message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("pages/interaction.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
