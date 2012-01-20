package org.recxx.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ComparisonResult {
	
	private final Object object1;
	private final Object object2;
	private final boolean different;
	private final BigDecimal absoluteDifference;
	private final BigDecimal percentageDifference;
	
	public ComparisonResult(Object object1, Object object2, boolean different, BigDecimal absoluteDifference, BigDecimal percentageDifference) {
		this.object1 = object1;
		this.object2 = object2;
		this.different = different;
		this.absoluteDifference = absoluteDifference;
		this.percentageDifference = percentageDifference;
	}

	public Object getObject1() {
		return object1;
	}
	
	public Object getObject2() {
		return object2;
	}
	
	public boolean isDifferent() {
		return different;
	}

	public BigDecimal getAbsoluteDifference() {
		return absoluteDifference;
	}

	public BigDecimal getPercentageDifference() {
		return percentageDifference;
	}

	public static final ComparisonResult valueOf(Object object1, Object object2, boolean different, BigDecimal absoluteDifference, BigDecimal percentageDifference) {
		return new ComparisonResult(object1, object2, different, absoluteDifference, percentageDifference);
	}

	public static final ComparisonResult valueOf(Object object1, Object object2, boolean different) {
		return new ComparisonResult(object1, object2, different, null, null);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, false);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

}
