package com.sp.in;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {

	private static final String query = "DELETE from book where book_id=?";
	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    PrintWriter pw = resp.getWriter();
	    
	    resp.setContentType("text/html");
	    
	    //get the Id for editing the record
	    int id = Integer.parseInt(req.getParameter("id"));
	    
	    
	    
	    // load the driver
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	    }catch(ClassNotFoundException cnf) {
	      cnf.printStackTrace();
	      
	    }
	    //generate the connection
	    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db","root","Sakshi@2829"); 
	      PreparedStatement ps = con.prepareStatement(query);){
	        ps.setInt(1, id);
	        
	        int count = ps.executeUpdate();
	        if(count==1) {
	          pw.println("<h2>Record is deleted</h2>");
	        }
	        else
	        {
	          pw.println("<h2>Record is not deleted</h2>");
	        }
	    }
	    catch(SQLException se) {
	      se.printStackTrace();
	      pw.println("<h1>" + se.getMessage()+"<h2>");
	    }catch(Exception e) {
	      e.printStackTrace();
	      pw.println("<h1>" + e.getMessage()+"<h2>");
	    }
	    pw.println("<a href='index.html'>Home</a>");
	    pw.println("<br>");
	    pw.println("<a href=\"booklist\">List Of Books</a>");
	    
	  }
	  
	  
	  @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    doGet(req,resp);
	  }
}

