package com.zuxp.servet3.servlet.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

@WebServlet(name = "testUpload", value = { "/asyncUpload" } ,asyncSupported = true)
@MultipartConfig()
public class AsyncTestUploadHttpServlet extends HttpServlet {
	private static final String path = "F:/test";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2905868989068765116L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(Thread.currentThread());
		final AsyncContext aysncContext = req.startAsync( req, resp);
		 aysncContext.start(new Runnable() {
			
			@Override
			public void run() {
				HttpServletRequest reqs = (HttpServletRequest) aysncContext.getRequest();
				Collection<Part> parts;
				try {
					parts = req.getParts();
					for (Part part : parts) {
						File file = new File(path, part.getSubmittedFileName());
						if(!file.exists()){
							file.createNewFile();
						}
						try (FileOutputStream os = new FileOutputStream(file);
								InputStream is = part.getInputStream()) {
							IOUtils.copy(is, os);
						}
						try {
							Thread.sleep(3000L);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						aysncContext.getResponse().getWriter().write("success");


				} catch (IOException | ServletException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}				
			
		});
		System.out.println(Thread.currentThread());

		resp.getWriter().write("success");
	}

}
