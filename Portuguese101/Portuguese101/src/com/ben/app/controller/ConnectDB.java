package com.ben.app.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ConnectDB
 */
@WebServlet(description = "connected to posgresql database", urlPatterns = { "/ConnectDB.do" })
public class ConnectDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ConnectDB() {
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
			StringBuffer jb = new StringBuffer();
			  String line = null;
			  try {
			    BufferedReader reader = request.getReader();
			    while ((line = reader.readLine()) != null)
			      jb.append(line);
			  } catch (Exception e) { /*report an error*/ }
			String Category = jb.toString();
			//System.out.println(Category);
			String query = "select \"EnglishNoun\",\"PortugueseNoun\" from \"Nouns\" where \"nounCategory\"='"+Category+"'";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet Rs = stmt.executeQuery();
			String table;
			table="<table><tr><th>English</th><th>Portuguese</th></tr>";
			while(Rs.next())
			{
				table = table + "<tr><td>"+Rs.getString(1)+"</td>";
				table = table + "<td>"+Rs.getString(2)+"</td></tr>";
				//System.out.println(Rs.getString(1).replaceAll("\\s+","")+ " | "+ Rs.getString(2).replaceAll("\\s+",""));
			}
			table = table + "</table>";
			response.setContentType("test/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(table);
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
