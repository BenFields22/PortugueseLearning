package com.ben.app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetCategories
 */
@WebServlet(description = "queries the categories of nouns", urlPatterns = { "/GetCategories.do" })
public class GetCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCategories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/portuguese","postgres","ben");
			String query = "select \"CategoryID\",\"CategoryName\" from \"NounCategories\"";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet Rs = stmt.executeQuery();
			String selector = "<select id=\"cat\">";
			
			while(Rs.next())
			{
				selector=selector+"<option value=\""+Rs.getString(1)+"\"> "+ Rs.getString(2)+"</option>";
				//System.out.println(Rs.getString(1));
			}
			selector=selector+"</select>";
			response.setContentType("test/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(selector);
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
