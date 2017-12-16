package com.zuxp.servet3.servlet.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

@WebServlet(name = "testUpload", value = { "/testUpload" })
@MultipartConfig()
public class TestUploadHttpServlet extends HttpServlet {
	private String path = "F:/test";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2905868989068765116L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			File file = new File(path, part.getSubmittedFileName());
			if(!file.exists()){
				file.createNewFile();
			}
			try (FileOutputStream os = new FileOutputStream(file);
					InputStream is = part.getInputStream()) {
				IOUtils.copy(is, os);
			}

		}
		resp.getWriter().write("success");
	}

}
