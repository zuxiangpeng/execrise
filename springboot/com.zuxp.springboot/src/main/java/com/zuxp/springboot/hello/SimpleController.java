package com.zuxp.springboot.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuxp.springboot.hello.properties.Book;


@Controller
@SpringBootApplication
public class SimpleController {

	
	@Autowired
	private Book bool;
	
	@RequestMapping("/")
	@ResponseBody
	public String hello(){
		return String.format( "hello world aa:%s;bb:%s;", bool.getAa(), bool.getBb());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleController.class, args);
	}
}
