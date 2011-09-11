package org.recxx.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.recxx.utils.ArrayUtils.isIndexOfLastArrayElement;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Shaine Ismail. User: sismail Date: 23/08/2011 Time: 18:28 Copyright SNI-Services ltd
 */
public class ArrayUtilsTest {

	@Test
	public void testMergeArrays() throws Exception {
		String[] one = new String[] { "one", "two" };
		String[] two = new String[] { "three", "four" };
		String[] result = new String[] { "one", "two", "three", "four" };
		String[] mergedResult = ArrayUtils.mergeArrays(one, two);
		Assert.assertEquals(result[0], mergedResult[0]);
		Assert.assertEquals(result[1], mergedResult[1]);
		Assert.assertEquals(result[2], mergedResult[2]);
		Assert.assertEquals(result[3], mergedResult[3]);
	}

	@Test(expected = NullPointerException.class)
	public void testMergeIfOneOFTheArraysIsNull() throws Exception {
		String[] one = new String[] { "one", "two" };
		String[] two = new String[] { "three", "four" };
		String[] result = new String[] { "one", "two", "three", "four" };
		Assert.assertEquals(result, ArrayUtils.mergeArrays(one, null));
		Assert.assertEquals(0, ArrayUtils.mergeArrays(null, null).length);
		Assert.assertEquals(two, ArrayUtils.mergeArrays(null, two));
	}

	@Test
	public void testMergeIfOneOFTheArraysIsEmpty() throws Exception {
		String[] one = new String[] { "one", "two" };
		String[] two = new String[] { "three", "four" };
		String[] empty = new String[] {};
		String[] result = new String[] { "one", "two", "three", "four" };
		Assert.assertEquals(one.length, ArrayUtils.mergeArrays(one, empty).length);
		Assert.assertEquals(0, ArrayUtils.mergeArrays(empty, empty).length);
		Assert.assertEquals(two.length, ArrayUtils.mergeArrays(empty, two).length);
	}

	@Test
	public void testCheckKeyColumnsWithMatchingRecords() throws Exception {
		List<String> key = new ArrayList<String>();
		key.add("test1");
		key.add("test2");
		List<String> columns = new ArrayList<String>();
		columns.add("test1");
		columns.add("test2");
		boolean test = ArrayUtils.keysPresentInColumns(key.toArray(new String[] {}), columns.toArray(new String[] {}));
		Assert.assertTrue(test);
	}

	@Test
	public void testCheckKeyColumnsWithMatchingWithMissingKey() throws Exception {
		List<String> key = new ArrayList<String>();
		key.add("test1");
		key.add("test2");
		List<String> columns = new ArrayList<String>();
		columns.add("test1");
		boolean test = ArrayUtils.keysPresentInColumns(key.toArray(new String[] {}), columns.toArray(new String[] {}));
		Assert.assertFalse(test);
	}

	@Test
	public void testDelimiterSplit() throws Exception {
		String line = "one two three four";
		String[] items = ArrayUtils.convertStringKeyToArray(line);
		Assert.assertEquals("one", items[0]);
		Assert.assertEquals("two", items[1]);
		Assert.assertEquals("three", items[2]);
		Assert.assertEquals("four", items[3]);

	}

	@Test
	public void testDelimiterSplitWillNullDelimiter() throws Exception {
		String line = "one two three four";
		String[] items = ArrayUtils.convertStringKeyToArray(line, null);
		Assert.assertEquals("one", items[0]);
		Assert.assertEquals("two", items[1]);
		Assert.assertEquals("three", items[2]);
		Assert.assertEquals("four", items[3]);
	}

	@Test
	public void testIndexOfKeyColumns() throws Exception {
		List<String> key = new ArrayList<String>();
		key.add("test1");
		key.add("test2");
		List<String> columns = new ArrayList<String>();
		columns.add("test1");
		columns.add("test2");

		List<Integer> test =
		        ArrayUtils.getColumnsPosition(key.toArray(new String[] {}), columns.toArray(new String[] {}));
		Assert.assertEquals(0, test.get(0).intValue());
		Assert.assertEquals(1, test.get(1).intValue());

	}

	@Test
	public void isIndexOfLastArrayElementShouldReturnFalseWhenIndexIsNotLastArrayElement() throws Exception {
		String[] array = { "1", "2", "3" };
		int notLastElementIndex = 1;
		assertThat(isIndexOfLastArrayElement(array, notLastElementIndex), is(false));
	}

	@Test
	public void isIndexOfLastArrayElementShouldReturnTrueWhenIndexIsLastArrayElement() throws Exception {
		String[] array = { "1", "2", "3" };
		int lastElementIndex = 2;
		assertThat(isIndexOfLastArrayElement(array, lastElementIndex), is(true));
	}
}
