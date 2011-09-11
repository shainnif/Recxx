package org.recxx.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Shaine Ismail. User: sismail Date: 23/08/2011 Time: 18:27
 */
public class ArrayUtils {

	public static <T> T[] mergeArrays(T[] one, T[] two) {
		T[] mergedArray = Arrays.copyOf(one, one.length + two.length);
		System.arraycopy(two, 0, mergedArray, one.length, two.length);
		return mergedArray;
	}

	/**
	 * Checks to see if the columns specified in the key[], are in the columns[]
	 * 
	 * @param keys
	 *            key to look for in the data set
	 * @param columns
	 *            columns to use in the reconciliation
	 * @return true if the key is in columns, false otherwise.
	 */
	public static boolean keysPresentInColumns(String[] keys, String[] columns) {
		boolean allKeysPresent = true;
		for (String key : keys) {
			if (!containsKey(columns, key)) {
				allKeysPresent = false;
			}
		}
		return allKeysPresent;
	}

	private static boolean containsKey(String[] columns, String key) {
		boolean columnExists = false;
		for (String column : columns) {
			if (key.equalsIgnoreCase(column)) {
				columnExists = true;
			}
		}
		return columnExists;
	}

	/**
	 * Converts the string key back into an array of key columns
	 * 
	 * @param keys
	 *            key to convert
	 * @param delimiter
	 *            used for the split
	 * @return String[]
	 */
	public static String[] convertStringKeyToArray(String keys, String delimiter) {
		if (delimiter == null) {
			delimiter = CONSTANTS.DELIMITER;
		}
		return keys.split(delimiter);
	}

	public static String[] convertStringKeyToArray(String keys) {
		return convertStringKeyToArray(keys, null);
	}

	/**
	 * return the position of the compare columns. By definition everything that isn't a key column is a compare column
	 * to make it easy to setup big queries. Of course, this means that the columns to compare must be in the same order
	 * 
	 * @param columns
	 *            columns used for compare
	 * @param keyColumns
	 *            columns used to match records.
	 * @return Integer[]
	 */
	public static int[] getCompareColumnsPosition(String[] columns, String[] keyColumns) {
		// get the location of the keys
		List<Integer> keyPositions = getColumnsPosition(columns, keyColumns);

		// now work out the location of the columns to compare by assuming
		// everything that isn't a key is a column to compare
		int[] comparePositions = new int[columns.length - keyPositions.size()];
		int count = 0;

		for (int i = 0; i < columns.length; i++) {
			boolean columnFound = false;
			for (int keyPosition : keyPositions) {
				if (i == keyPosition) {
					columnFound = true;
					break;
				}
			}

			if (!columnFound) {
				comparePositions[count] = i;
				count++;
			}
		}

		return comparePositions;

	}

	/**
	 * return an int[] of the positions of the keyColumns, in the columns[]
	 * 
	 * @param columns
	 *            column names
	 * @param keyColumns
	 *            key columns
	 * @return int[] positions of key columns in the column[]
	 */
	public static List<Integer> getColumnsPosition(String[] columns, String[] keyColumns) {
		// first time in, set the positions of the key in relation to the data
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for (String key : keyColumns) {
			for (int j = 0; j < columns.length; j++) {
				if (key.equalsIgnoreCase(columns[j].trim())) {
					positions.add(j);
					break;
				}
			}
		}
		return positions;
	}

	public static boolean isIndexOfLastArrayElement(String[] array, int elementIndex) {
		return elementIndex == array.length - 1;
	}

}
