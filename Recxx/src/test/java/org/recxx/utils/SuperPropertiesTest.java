package org.recxx.utils;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

public class SuperPropertiesTest {

	
	private static final String _01_APR_2011 = "01-Apr-2011";
	private static final String TEST_PROPERTY = "testProperty";
	private static final String TEST_VALUE = "testValue";
	private static final String TEST_PROPERTY_PLUS_DATE_PROPERTY = "testPropertyPlusDateProperty";
	private static final String TEST_VALUE_PLUS_DATE_PROPERTY = "testValue${BUSINESS_DATE}";
	private static final String TEST_PROPERTY_PLUS_NOT_SET_PROPERTY = "testPropertyPlusNotSetProperty";
	private static final String TEST_VALUE_PLUS_NOT_SET_PROPERTY = "testValue${NOT_SET_RPOPERTY}";
	
	private SuperProperties props;
	{
		props = new SuperProperties();
		props.put(TEST_PROPERTY, TEST_VALUE);
		props.put(TEST_PROPERTY_PLUS_DATE_PROPERTY, TEST_VALUE_PLUS_DATE_PROPERTY);
		props.put(TEST_PROPERTY_PLUS_NOT_SET_PROPERTY, TEST_VALUE_PLUS_NOT_SET_PROPERTY);
		System.setProperty("BUSINESS_DATE", _01_APR_2011);
	}
	
	@Test
	public void testGetProperty() {
		assertEquals("Properties values should be the same!", props.getProperty(TEST_PROPERTY), TEST_VALUE);
	}

	@Test
	public void testGetPropertyWithProperty() {
		assertEquals("Properties values should be the same!", props.getProperty(TEST_PROPERTY_PLUS_DATE_PROPERTY), TEST_VALUE + _01_APR_2011);
	}

	@Test
	public void testGetPropertyWithUnsetProperty() {
		assertNull(props.getProperty("UnsetProperty"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetPropertyWithUnsetConfirguredProperty() {
		assertEquals("Properties values should be the same!", props.getProperty(TEST_PROPERTY_PLUS_NOT_SET_PROPERTY), TEST_VALUE + _01_APR_2011);
	}
	
	

}
