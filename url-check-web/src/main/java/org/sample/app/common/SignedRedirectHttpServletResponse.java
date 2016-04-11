package org.sample.app.common;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * HttpServletResponse which is used for avoiding open-redirect vulnerability.
 *
 * This class extends {@code HttpServletResponseWrapper} and the constructor
 * takes three additional parameters. The first one of these is a HMAC signature
 * to validate the target URL of redirect. The second is a secret key used for
 * signing. And the last one is a set of URLs which are free from validation. 
 * 
 * When the method {@code sendRedirect} is called, a HMAC signature of the
 * target URL is calculated and compared with the one provided by constructor.
 * If these values are not identical, the request might have been falsified.
 */
public class SignedRedirectHttpServletResponse extends
		HttpServletResponseWrapper {

	private SignCalculator signCalculator;

	private String sign;

	private Set<String> excludedURLs;

	public SignedRedirectHttpServletResponse(HttpServletResponse response,
			SignCalculator signCalculator, String sign, Set<String> excludedURLs) {
		super(response);
		this.signCalculator = signCalculator;
		this.sign = sign;
		this.excludedURLs = excludedURLs;
	}

	/**
	 * Validate a HMAC signature of the target URL. If it doesn't match
	 * signature provided by constructor, this method throw an exception.
	 */
	@Override
	public void sendRedirect(String location) throws IOException {
		if(!excludedURLs.contains(location)){			
			Assert.notNull(signCalculator);
			Assert.notNull(sign);
			
			if (!StringUtils.hasLength(sign)) {
				throw new InvalidRedirectException("Sign is missing.");
			}

			if (!signCalculator.validateSign(location, sign)) {
				throw new InvalidRedirectException("Invalid sign.");
			}
		}

		super.sendRedirect(location);
	}

}
