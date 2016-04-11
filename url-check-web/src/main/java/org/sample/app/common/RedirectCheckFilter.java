package org.sample.app.common;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

/**
 * The filter which is used for avoiding open-redirect vulnerability.
 *
 * If a parameter matching the value of {@code redirectParameter} has been set
 * on the request, the response is replaced with an instance of
 * {@link SignedRedirectHttpServletResponse}. It is instantiated with a HMAC
 * signature and a secret key used for signing to confirm whether the target URL
 * of redirect is intended by the user.
 */
public class RedirectCheckFilter extends GenericFilterBean {

	/**
	 * The name of the parameter containing the HMAC signature for target URL of
	 * redirect.
	 */
	private String signParameter = "sign";

	private SignCalculator signCalculator;

	/**
	 * The name of the parameter containing the target URL of redirect.
	 */
	private String redirectParameter = "redirectTo";

	/**
	 * The set of URLs excluded from sign checking.
	 */
	private Set<String> excludedURLs = Collections.emptySet();

	public void setSignParameter(String signParameter) {
		Assert.notNull(signParameter);
		this.signParameter = signParameter;
	}
	
	public void setRedirectParameter(String redirectParameter) {
		Assert.notNull(redirectParameter);
		this.redirectParameter = redirectParameter;
	}
	
	public void setExcludedURLs(Set<String> excludedURLs) {
		this.excludedURLs = excludedURLs;
	}
	
	public RedirectCheckFilter(SignCalculator signCalculator) {
		Assert.notNull(signCalculator);
		this.signCalculator = signCalculator;
	}
	
	/**
	 * If the current request contains the parameter which is specified by
	 * {@code redirectParamenter}, this method return the instance of
	 * {@link SignedRedirectHttpServletResponse} to validate signature when it
	 * redirect.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		request.setAttribute("RedirectCheckFilter.signCalculator", signCalculator);
		request.setAttribute("RedirectCheckFilter.redirectParameter", redirectParameter);
		request.setAttribute("RedirectCheckFilter.signParameter", signParameter);
		if (StringUtils.hasLength(request.getParameter(redirectParameter))) {
			String sign = request.getParameter(signParameter);
			HttpServletResponse signeidResponse = new SignedRedirectHttpServletResponse(
					(HttpServletResponse) response, signCalculator, sign, excludedURLs);
			filterChain.doFilter(request, signeidResponse);
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
