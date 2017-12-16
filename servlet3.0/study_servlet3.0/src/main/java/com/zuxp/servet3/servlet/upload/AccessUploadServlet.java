package com.zuxp.servet3.servlet.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="upload" , value={"/upload"})
public class AccessUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4054220045183279100L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/asyncUpload.jsp").forward(req, resp);
		
	}
}
