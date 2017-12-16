package com.zuxp.servet3.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zuxp.servet3.pojo.User;

@WebServlet(name="asyncHello" , value={"/asyncHello"}, asyncSupported =true)
public class AsyncHelloServlet extends HttpServlet{

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
		 
		 AsyncContext aysncContext = req.startAsync( req, resp);
		 
		 aysncContext.start(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		 aysncContext.addListener(new AsyncListener() {
			
			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(AsyncEvent event) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				// TODO Auto-generated method stub
				
			}
		}, req, resp);
		 
		
//		 req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	

}
