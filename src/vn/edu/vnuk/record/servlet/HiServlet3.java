package vn.edu.vnuk.record.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi-servlet3")
@SuppressWarnings("serial")
public class HiServlet3 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<title> First servlet</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1> Our first servlet 3</h1>");
		out.print("</body>");
	}
}
