package org.sample.app.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.Assert;

public class RedirectorTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String targetUrl;

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	@Override
	public int doStartTag() throws JspException {
		Assert.hasLength(targetUrl);
		SignCalculator signCalculator = (SignCalculator) pageContext
				.getRequest()
				.getAttribute("RedirectCheckFilter.signCalculator");
		String redirectParameter = (String) pageContext.getRequest()
				.getAttribute("RedirectCheckFilter.redirectParameter");
		String signParameter = (String) pageContext.getRequest().getAttribute(
				"RedirectCheckFilter.signParameter");
		StringBuilder builder = new StringBuilder(
				"<input type=\"hidden\" name=\"").append(redirectParameter)
				.append("\" value=\"").append(targetUrl).append("\" />\n")
				.append("<input type=\"hidden\" name=\"").append(signParameter)
				.append("\" value=\"")
				.append(signCalculator.calculateSign(targetUrl))
				.append("\" />");
		try {
			pageContext.getOut().write(builder.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}

}
