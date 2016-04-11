package org.sample.app.common;

import org.springframework.util.Assert;

public abstract class SignCalculator {
	
	/**
	 * The secret key for signing.
	 */
	protected String key;
	
	public SignCalculator(String key){
		Assert.notNull(key);
		this.key = key;
	}
	
	abstract public String calculateSign(String target);
	
	public boolean validateSign(String target, String sign){
		return sign.equals(calculateSign(target));
	}
}
