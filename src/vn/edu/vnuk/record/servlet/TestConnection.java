/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnuk.record.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.edu.vnuk.record.jdbc.ConnectionFactory;

/**
 *
 * @author michel
 */
@SuppressWarnings({ "serial", "unused" })
@WebServlet("/test-connection")
public class TestConnection extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();
		
        try {
        	Connection connection = new ConnectionFactory().getConnection();
            
        	System.out.println("Connection is open!");
            connection.close();
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            System.out.println("Done");
        }
    }
}
