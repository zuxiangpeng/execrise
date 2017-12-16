package com.zuxp.servet3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zuxp.servet3.pojo.User;

@WebServlet(name="hello" , value={"/hello"})
public class HelloServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6379451650236845064L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		 User user = new User();
		 
		 user.setId("1000001");
		 
		 user.setName("tom");
		 
		 user.setSex("mmm");
		 
		 req.setAttribute("user", user);
		 
		 
		 req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	

}
