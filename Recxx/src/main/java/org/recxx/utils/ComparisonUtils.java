package org.recxx.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.recxx.domain.ComparisonResult;

public class ComparisonUtils {
	
	public static final BigDecimal DEFAULT_TOLERANCE_PERCENTAGE = BigDecimal.valueOf(0.01);
	public static final BigDecimal DEFAULT_SMALLEST_ABSOLUTE_VALUE = BigDecimal.valueOf(0.00001);

	public static BigDecimal absoluteDifference(BigDecimal o1, BigDecimal o2) {
		return o1.subtract(o2).abs();
	}

	public static BigDecimal percentageDifference(BigDecimal o1, BigDecimal o2) {
		BigDecimal original = o1.compareTo( BigDecimal.ZERO ) == 0  ? o2 : o1;
		return o2.subtract(o1).divide(original, 6, RoundingMode.HALF_UP).multiply( BigDecimal.valueOf(100) );
	}
	
	public static ComparisonResult compare(Object o1, Object o2) {
		return compare(o1, o2, DEFAULT_SMALLEST_ABSOLUTE_VALUE, DEFAULT_TOLERANCE_PERCENTAGE);
	}
	
	public static ComparisonResult compare(Object o1, Object o2, boolean equalsIgnoreCase) {
		return compare(o1, o2, DEFAULT_SMALLEST_ABSOLUTE_VALUE, DEFAULT_TOLERANCE_PERCENTAGE, equalsIgnoreCase);
	}
	
	public static ComparisonResult compare(Object o1, Object o2, BigDecimal smallestAbsoluteValue, BigDecimal tolerancePercentage) {
		return compare(o1, o2, smallestAbsoluteValue, tolerancePercentage, false);
	}	
		
	public static ComparisonResult compare(Object o1, Object o2, BigDecimal smallestAbsoluteValue, BigDecimal tolerancePercentage, boolean equalsIgnoreCase) {
		ComparisonResult result = null;		
		if (o1 instanceof Number && o2 instanceof Number) {
			BigDecimalConverter converter = new BigDecimalConverter();
			result = compareNumeric((BigDecimal)converter.convert(BigDecimal.class, o1), 
									(BigDecimal)converter.convert(BigDecimal.class, o2), 
									smallestAbsoluteValue, tolerancePercentage);
		}
		else {
			if (o1 != null && o2 != null && o1.getClass() != o2.getClass()) {
				throw new UnsupportedOperationException(o1.getClass().getName() + " cannot be compared to " + o2.getClass().getName());
			}
			result = compareNonNumeric(o1, o2, equalsIgnoreCase);
		}
		return result;
	}
		
	private static ComparisonResult compareNonNumeric(Object o1, Object o2, boolean equalsIgnoreCase) {
		if (o1 == null && o2 == null) {
			return ComparisonResult.valueOf(o1, o2, false);	
		} 
		else if (o1 == null || o2 == null) {
			return ComparisonResult.valueOf(o1, o2, true);	
		}
		else {
			if (o1 instanceof String && o2 instanceof String && equalsIgnoreCase) {
				return ComparisonResult.valueOf(o1, o2, !((String)o1).equalsIgnoreCase((String)o2));
			}
			return ComparisonResult.valueOf(o1, o2, !o1.equals(o2));
		}	
	}
	
	private static ComparisonResult compareNumeric(BigDecimal o1, BigDecimal o2, BigDecimal smallestAbsoluteValue, BigDecimal tolerancePercentage) {
		boolean difference = false;
		if ((o1 == null || o2 == null)) {
			if (!(o1 == null && o2 == null)) { 
				difference = true;	
			}
		}
		else if ((o1.abs().compareTo(smallestAbsoluteValue)) == 1 || (o2.abs().compareTo(smallestAbsoluteValue) == 1)) {
			BigDecimal percentageDifference = percentageDifference(o1, o2);
			BigDecimal absoluteDifference = absoluteDifference(o1, o2);
			if (percentageDifference.abs().compareTo(tolerancePercentage) == 1) {
				difference = true;
			}
			return ComparisonResult.valueOf(o1, o2, difference, absoluteDifference, percentageDifference);
		}
		return ComparisonResult.valueOf(o1, o2, difference, null, null);
	}

	
}
