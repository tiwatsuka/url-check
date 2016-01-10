package org.sample.app.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class RedirectCheckFilter extends GenericFilterBean {

	private String signParameter = "sign";
	
	private String key = "HMACDefaultSecretKey";
	
	public void setSignParameter(String signParameter) {
		this.signParameter = signParameter;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String sign = request.getParameter(signParameter);
		HttpServletResponse signeidResponse = new SignedRedirectHttpServletResponse((HttpServletResponse)response, key, sign);
		filterChain.doFilter(request, signeidResponse);
	}

}
