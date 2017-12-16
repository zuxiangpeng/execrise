package com.zuxp.servet3.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
@WebFilter(filterName="Filter1＿AAA", value={"/*"}, asyncSupported = true)
public class Filter1＿AAA  implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	System.out.println("这里时filte11111111");
		
		chain.doFilter(request, response);
		
		System.out.println("filter111 执行完毕");		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
