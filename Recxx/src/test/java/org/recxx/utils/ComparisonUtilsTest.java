package org.recxx.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;


public class ComparisonUtilsTest {

	String nullString = null;
	BigDecimal nullBigDecimal = null;
	Date nullDate = null;

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
		assertEquals(false, ComparisonUtils.compare(Integer.valueOf(1), Long.valueOf(1l)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Integer.valueOf(1), Long.valueOf(2l)).isDifferent());
		assertEquals(true, ComparisonUtils.compare(Integer.valueOf(1), Long.valueOf(-1l)).isDifferent());
	}
	
}
