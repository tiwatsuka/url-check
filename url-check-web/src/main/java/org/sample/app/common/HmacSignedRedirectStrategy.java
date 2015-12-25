package org.sample.app.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class HmacSignedRedirectStrategy extends DefaultRedirectStrategy {

	private String signParameter;
	
	private String key;
	
	public HmacSignedRedirectStrategy() {
		super();
		signParameter = "sign";
		key = "HMACDefaultSecretKey";
	}
	
	public void setSignParameter(String signParameter) {
		Assert.notNull(signParameter, "SignParameter must not be null");
		this.signParameter = signParameter;
	}
	
	public void setKey(String key){
		Assert.notNull(key, "Key must not be null");
		this.key = key;
	}
	
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		String sign = request.getParameter(signParameter);
		
		if(!StringUtils.hasText(sign)){
			throw new InvalidRedirectException("Sign is missing.");
		}
		
		if(!sign.equals(HmacUtils.hmacSha256Hex(key, url))){
			throw new InvalidRedirectException("Invalid sign.");
		}
		
		super.sendRedirect(request, response, url);
		
	}

}
