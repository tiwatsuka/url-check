package org.sample.app.common;

import org.apache.commons.codec.digest.HmacUtils;

public class HmacSignCalculator extends SignCalculator {

	public HmacSignCalculator(String key) {
		super(key);
	}

	@Override
	public String calculateSign(String target) {
		return HmacUtils.hmacSha256Hex(key, target);
	}

}
