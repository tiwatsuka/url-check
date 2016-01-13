package org.sample.app.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class SignedRedirectHttpServletResponse extends HttpServletResponseWrapper {

	private String key;
	
	private String sign; 
	
	public SignedRedirectHttpServletResponse(HttpServletResponse response, String key, String sign) {
		super(response);
		this.key = key;
		this.sign = sign;
	}

	@Override
	public void sendRedirect(String location) throws IOException{
		Assert.notNull(key);
		Assert.notNull(sign);
		
		if(!StringUtils.hasLength(sign)){
			throw new InvalidRedirectException("Sign is missing.");
		}

		if(!sign.equals(HmacUtils.hmacSha256Hex(key, location))){
			throw new InvalidRedirectException("Invalid sign.");
		}
		
		super.sendRedirect(location);
	}
	
}
