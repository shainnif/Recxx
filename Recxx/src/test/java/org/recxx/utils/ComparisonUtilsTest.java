package org.recxx.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class ComparisonUtilsTest {

	private String nullString = null;
	private Calendar dateCalendarOne = new GregorianCalendar(1, Calendar.JANUARY, 2012);
	private Calendar dateCalendarTwo = new GregorianCalendar(2, Calendar.JANUARY, 2012);
	private Date dateOne = dateCalendarOne.getTime();
	private Date dateTwo = dateCalendarTwo.getTime();
	private Date nullDate = null;
	private BigDecimal nullBigDecimal = null;
	private byte oneByte = 1;
	private byte minusOneByte = -1;
	private byte twoByte = 2;
	private short oneShort = 1;
	private short minusOneShort = -1;
	private short twoShort = 2;
	
	@Test
	public void testStrings() {
		assertEquals(true, ComparisonUtils.compare("Sausage", "Bacon").isDifferent());
		assertEquals(true, ComparisonUtils.compare("Sausage", "sausage").isDifferent());
		assertEquals(false, ComparisonUtils.compare("Sausage", "sausage", true).isDifferent());
		assertEquals(false, ComparisonUtils.compare("Sausage", "Sausage").isDifferent());
	}

	@Test
	public void testNullStrings() {
		assertEquals(true, ComparisonUtils.compare(nullString, "Sausage").isDifferent());
		assertEquals(true, ComparisonUtils.compare("Sausage", nullString).isDifferent());
		assertEquals(false, ComparisonUtils.compare(nullString, nullString).isDifferent());
	}

	@Test
	public void testDates() {
		assertEquals(true, ComparisonUtils.compare(dateOne, dateTwo).isDifferent());
		assertEquals(false, ComparisonUtils.compare(dateOne, dateOne).isDifferent());
	}

	@Test
	public void testNullDates() {
		assertEquals(true, ComparisonUtils.compare(nullDate, dateOne).isDifferent());
		assertEquals(true, ComparisonUtils.compare(dateOne, nullDate).isDifferent());
		assertEquals(false, ComparisonUtils.compare(nullDate, nullDate).isDifferent());
	}

	@Test
	public void testBytes() {
		assertEquals(true, ComparisonUtils.compare(oneByte, twoByte).isDifferent());
		assertEquals(false, ComparisonUtils.compare(oneByte, oneByte).isDifferent());
		assertEquals(true, ComparisonUtils.compare(oneByte, minusOneByte).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Byte.valueOf(oneByte), Byte.valueOf(oneByte)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Byte.valueOf(oneByte), Byte.valueOf(twoByte)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Byte.valueOf(oneByte), Byte.valueOf(minusOneByte)).isDifferent());
	}

	@Test
	public void testBytesCrossedWithShorts() {
		assertEquals(true, ComparisonUtils.compare(oneByte, twoShort).isDifferent());
		assertEquals(false, ComparisonUtils.compare(oneByte, oneShort).isDifferent());
		assertEquals(true, ComparisonUtils.compare(oneByte, minusOneShort).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Byte.valueOf(oneByte), Short.valueOf(oneShort)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Byte.valueOf(oneByte), Short.valueOf(twoShort)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Byte.valueOf(oneByte), Short.valueOf(minusOneShort)).isDifferent());
	}

	@Test
	public void testShorts() {
		assertEquals(true, ComparisonUtils.compare(oneShort, twoShort).isDifferent());
		assertEquals(false, ComparisonUtils.compare(oneShort, oneShort).isDifferent());
		assertEquals(true, ComparisonUtils.compare(oneShort, minusOneShort).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Short.valueOf(oneShort), Short.valueOf(oneShort)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Short.valueOf(oneShort), Short.valueOf(twoShort)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Short.valueOf(oneShort), Short.valueOf(minusOneShort)).isDifferent());
	}
	
	@Test
	public void testShortsCrossedWithInts() {
		assertEquals(true, ComparisonUtils.compare(oneShort, 2).isDifferent());
		assertEquals(false, ComparisonUtils.compare(oneShort, 1).isDifferent());
		assertEquals(true, ComparisonUtils.compare(oneShort, -1).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Short.valueOf(oneShort), Integer.valueOf(1)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Short.valueOf(oneShort), Integer.valueOf(2)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Short.valueOf(oneShort), Integer.valueOf(-1)).isDifferent());
	}

	@Test
	public void testInts() {
		assertEquals(true, ComparisonUtils.compare(1, 2).isDifferent());
		assertEquals(false, ComparisonUtils.compare(1, 1).isDifferent());
		assertEquals(true, ComparisonUtils.compare(1, -1).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Integer.valueOf(1), Integer.valueOf(1)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Integer.valueOf(1), Integer.valueOf(2)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Integer.valueOf(1), Integer.valueOf(-1)).isDifferent());
	}

	@Test
	public void testIntsCrossedWithLongs() {
		assertEquals(true, ComparisonUtils.compare(1, 2l).isDifferent());
		assertEquals(false, ComparisonUtils.compare(1, 1l).isDifferent());
		assertEquals(true, ComparisonUtils.compare(1, -1l).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Integer.valueOf(1), Long.valueOf(1)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Integer.valueOf(1), Long.valueOf(2)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Integer.valueOf(1), Long.valueOf(-1)).isDifferent());
	}

	@Test
	public void testLongs() {
		assertEquals(true, ComparisonUtils.compare(1l, 2l).isDifferent());
		assertEquals(false, ComparisonUtils.compare(1l, 1l).isDifferent());
		assertEquals(true, ComparisonUtils.compare(1l, -1l).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Long.valueOf(1), Long.valueOf(1)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Long.valueOf(1), Long.valueOf(2)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Long.valueOf(1), Long.valueOf(-1)).isDifferent());
	}

	@Test
	public void testFloats() {
		assertEquals(true, ComparisonUtils.compare(1f, 2f).isDifferent());
		assertEquals(false, ComparisonUtils.compare(1f, 1f).isDifferent());
		assertEquals(true, ComparisonUtils.compare(1f, -1f).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Float.valueOf(1f), Float.valueOf(1f)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Float.valueOf(1f), Float.valueOf(2f)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Float.valueOf(1f), Float.valueOf(-1f)).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Float.valueOf(1.0124f), Float.valueOf(1.0123f)).isDifferent());  // Below default tolerance level
		assertEquals(false, ComparisonUtils.compare(Float.valueOf(0.00000124f), Float.valueOf(0.00000123f)).isDifferent());  // Below default smallest absolute minimum level
		assertEquals(true, ComparisonUtils.compare(Float.valueOf(1.0124f), Float.valueOf(1.0123f),BigDecimal.valueOf(0),BigDecimal.valueOf(0)).isDifferent());
	}

	@Test
	public void testFloatsCrossedWithDoubles() {
		assertEquals(true, ComparisonUtils.compare(1f, 2d).isDifferent());
		assertEquals(false, ComparisonUtils.compare(1f, 1d).isDifferent());
		assertEquals(true, ComparisonUtils.compare(1f, -1d).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Float.valueOf(1f), Double.valueOf(1d)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Float.valueOf(1f), Double.valueOf(2d)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Float.valueOf(1f), Double.valueOf(-1d)).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Float.valueOf(1.0124f), Double.valueOf(1.0123d)).isDifferent());  // Below default tolerance level
		assertEquals(false, ComparisonUtils.compare(Float.valueOf(0.00000124f), Double.valueOf(0.00000123d)).isDifferent());  // Below default smallest absolute minimum level
		assertEquals(true, ComparisonUtils.compare(Float.valueOf(1.0124f), Double.valueOf(1.0123d),BigDecimal.valueOf(0),BigDecimal.valueOf(0)).isDifferent());
	}
	
	@Test
	public void testDoubles() {
		assertEquals(true, ComparisonUtils.compare(1d, 2d).isDifferent());
		assertEquals(false, ComparisonUtils.compare(1d, 1d).isDifferent());
		assertEquals(true, ComparisonUtils.compare(1d, -1d).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Double.valueOf(1), Double.valueOf(1)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Double.valueOf(1), Double.valueOf(2)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Double.valueOf(1), Double.valueOf(-1)).isDifferent());
		assertEquals(false, ComparisonUtils.compare(Double.valueOf(1.0124d), Double.valueOf(1.0123d)).isDifferent());  // Below default tolerance level
		assertEquals(false, ComparisonUtils.compare(Double.valueOf(0.00000124d), Double.valueOf(0.00000123d)).isDifferent());  // Below default smallest absolute minimum level
		assertEquals(true, ComparisonUtils.compare(Double.valueOf(1.0124d), Double.valueOf(1.0123d),BigDecimal.valueOf(0),BigDecimal.valueOf(0)).isDifferent());
	}

	@Test
	public void testBigDecimals() {
		assertEquals(true, ComparisonUtils.compare(BigDecimal.valueOf(1d), nullBigDecimal).isDifferent());
		assertEquals(true, ComparisonUtils.compare(BigDecimal.valueOf(1d), BigDecimal.valueOf(2d)).isDifferent());
		assertEquals(false, ComparisonUtils.compare(BigDecimal.valueOf(1d), BigDecimal.valueOf(1d)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(BigDecimal.valueOf(1d), BigDecimal.valueOf(-1d)).isDifferent());
		assertEquals(false, ComparisonUtils.compare(BigDecimal.valueOf(1), BigDecimal.valueOf(1)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(BigDecimal.valueOf(1), BigDecimal.valueOf(2)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(BigDecimal.valueOf(1), BigDecimal.valueOf(-1)).isDifferent());
		assertEquals(false, ComparisonUtils.compare(BigDecimal.valueOf(1.0124d), BigDecimal.valueOf(1.0123d)).isDifferent());  // Below default tolerance level
		assertEquals(false, ComparisonUtils.compare(BigDecimal.valueOf(0.00000124d), BigDecimal.valueOf(0.00000123d)).isDifferent());  // Below default smallest absolute minimum level
		assertEquals(true, ComparisonUtils.compare(BigDecimal.valueOf(1.0124d), BigDecimal.valueOf(1.0123d),BigDecimal.valueOf(0),BigDecimal.valueOf(0)).isDifferent());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testNonComparables() {
		assertEquals(true, ComparisonUtils.compare(dateOne, oneByte).isDifferent());
	}
	
}
