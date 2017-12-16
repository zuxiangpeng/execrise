package com.zuxp.springboot.hello.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties( prefix ="book")
public class Book {
	
	private String aa;

	private String bb;

	public String getAa() {
		return aa;
	}

	public void setAa(String aa) {
		this.aa = aa;
	}

	public String getBb() {
		return bb;
	}

	public void setBb(String bb) {
		this.bb = bb;
	}
	
	public static void main(String[] args) {
		
	}
	

}
