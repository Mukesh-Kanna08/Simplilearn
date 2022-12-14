package com.simplilearn.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc_database")
	private DataSource dataSource;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			
			connection = dataSource.getConnection();
		String sql = "select * from students";
		statement = connection.createStatement();
		result = statement.executeQuery(sql);
		while(result.next()) {
			String fname = result.getString("fname");
      		out.println(fname);	
		}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

}
