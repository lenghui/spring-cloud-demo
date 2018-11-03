package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns="/*")
public class EncodeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request.getCharacterEncoding() != null) {
			System.out.println(request.getCharacterEncoding());
		}
		// 设置字符编码为UTF-8
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.toString());
		chain.doFilter(request, response);
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
