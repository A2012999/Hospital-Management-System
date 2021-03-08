package com.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.ConnectionProvider;


/**
 * Servlet implementation class LoginDao
 */
@WebServlet("/LoginDao")
public class LoginDao extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			HttpSession hs = request.getSession();
			Connection con = ConnectionProvider.getCon();
			System.out.println(con);
			Statement stmt = con.createStatement();
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery("select name,email,password from user where email='" + email + "'and password='" + password + "'");
			
			System.out.println("A");
			if (rs.next()) {
				hs.setAttribute("uname", rs.getString(1));
				System.out.println("B");
				response.sendRedirect("department.jsp");
			} else {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// doGet(request, response);
	}

}
