package org.sample.app.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class RedirectCheckFilter extends GenericFilterBean {

	private String signParameter = "sign";
	
	private String key = "HMACDefaultSecretKey";
	
	private String redirectParameter = "redirectTo";
	
	public void setSignParameter(String signParameter) {
		Assert.notNull(signParameter);
		this.signParameter = signParameter;
	}
	
	public void setKey(String key) {
		Assert.notNull(key);
		this.key = key;
	}
	
	public void setRedirectParameter(String redirectParameter) {
		Assert.notNull(redirectParameter);
		this.redirectParameter = redirectParameter;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if(StringUtils.hasLength(request.getParameter(redirectParameter))){
			String sign = request.getParameter(signParameter);
			HttpServletResponse signeidResponse = new SignedRedirectHttpServletResponse((HttpServletResponse)response, key, sign);
			filterChain.doFilter(request, signeidResponse);
		}else{
			filterChain.doFilter(request, response);
		}
	}

}
