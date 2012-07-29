package org.recxx.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import static org.recxx.utils.ArrayUtils.isIndexOfLastArrayElement;
import static org.recxx.utils.StringUtils.isNullOrEmpty;

/**
 * <p>
 * Generic class to enable logging of values to a csv file.
 * </p>
 * 
 * <p>
 * Sample Usage:
 * </p>
 * 
 * <p>
 * 1) Create a new instance of CSVLogger (filename must contain an extension like .csv or .txt)
 * </p>
 * 
 * <pre>
 * CSVLogger foo = new CSVLogger("/home/foo/file.csv", true)
 * </pre>
 * 
 * <p>
 * 2) Set any parameters needed and prepare the file for logging
 * </p>
 * 
 * <pre>
 * foo.setFormatStamp(new SimpleDatFormat(&quot;ddMMyyyyHHmm&quot;));
 * foo.open();
 * </pre>
 * 
 * <p>
 * This will create a file /home/foo/file050720001043.csv if the date were 05/07/2000 and the time were 10:43.
 * </p>
 * 
 * <p>
 * 3) You can then write out either strings or arrays
 * </p>
 * 
 * <pre>
 * foo.write(&quot;sample&quot;);
 * </pre>
 * <p>
 * will write out "sample," (the delimiter of a comma is the default)
 * </p>
 * 
 * <pre>
 * foo.writeLine(&quot;sample&quot;);
 * </pre>
 * 
 * <p>
 * will write out "sample" followed by a line feed character for a new line
 * </p>
 * 
 * <pre>
 * foo.writeLine(new String[{"I"},{"want"},{"this"},{"text"},{"delimited"}]);
 * </pre>
 * 
 * <p>
 * will write out "I,want,this,text,delimited" followed by a line feed character
 * </p>
 * 
 * <p>
 * 4) Once finished, close the CSVLogger
 * </p>
 * 
 * <pre>
 * foo.close();
 * </pre>
 */
public class CSVLogger {

	private static final String QUOTE = "\"";

	private static final Logger LOGGER = Logger.getLogger(CSVLogger.class.getName());

	public static final String DEFAULT_DELIMITER = ",";

	public static final String DEFAULT_NULL_STRING = "";

	private String filename;

	private BufferedWriterManager bufferedWriterManager;

	private String delimiter = DEFAULT_DELIMITER;

	private String nullString = DEFAULT_NULL_STRING;

	private File file;

	private BufferedWriter writer;

	public void setFilename(String filename) throws IllegalArgumentException {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public BufferedWriterManager getBufferedWriterManager() {
		return bufferedWriterManager;
	}

	public void setBufferedWriterManager(BufferedWriterManager bufferedWriterManager) {
		this.bufferedWriterManager = bufferedWriterManager;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * return the default value to be written to file when a null or space is encountered in the value to be written.
	 * <p/>
	 * Note: BCP formatted csv files need ,, to input a null in BCP, while StringTokenizers reading a formatted CSV file
	 * will need , ,
	 * 
	 * @param
	 */
	public void setNullString(String nullString) {
		this.nullString = nullString;
	}

	/**
	 * return the default value to be written to file when a null or space is encountered in the value to be written.
	 * <p/>
	 * Note: BCP formatted csv files need ,, to input a null in BCP, while StringTokenizers reading a formatted CSV file
	 * will need , ,
	 * 
	 * @return java.lang.String
	 */
	public String getNullString() {
		return nullString;
	}

	public void open() throws IOException {
		file = new File(filename);
		writer = bufferedWriterManager.open(file);
		LOGGER.info("Created csv file " + file.getPath());
	}

	public void close() throws IOException {
		bufferedWriterManager.close();
	}

	public void write(double value) throws IOException {
		write(String.valueOf(value));
	}

	public void write(float value) throws IOException {
		write(String.valueOf(value));
	}

	public void write(int value) throws IOException {
		write(String.valueOf(value));
	}

	public void write(String string) throws IOException {
		if (isNullOrEmpty(string)) {
			string = nullString;
		}
		else if (string.contains(delimiter) && !string.startsWith(QUOTE) && !string.endsWith(QUOTE)) {
			string = QUOTE + string + QUOTE;
		}
		writer.write(string + delimiter);
	}

	public void write(Date date) throws IOException {
		String stringValue = "";
		if (date != null) {
			stringValue += date;
		}
		write(stringValue);
	}

	/**
	 * Loops through an array and outputs accordingly, using the delimiter to separate ever entry, except the last one
	 * which is followed by a line feed.
	 * 
	 * @param values
	 *            array to write to file
	 * @throws java.io.IOException
	 *             problem writing to the file
	 */
	public void writeLine(String[] values) throws IOException {
		int idx = 0;
		for (String value : values) {
			if (isIndexOfLastArrayElement(values, idx)) {
				writeLine(value);
			} else {
				write(value);
			}
			idx++;
		}
	}

	public void writeLine(String value) throws IOException {
		if (isNullOrEmpty(value)) {
			value = nullString;
		}
		writer.write(value);
		writer.newLine();
	}

	public void writeLine(Date value) throws IOException {
		String stringValue = "";
		if (value != null) {
			stringValue += value;
		}
		writeLine(stringValue);
	}

	public void writeLine(int value) throws IOException {
		writeLine(String.valueOf(value));
	}

	public void writeLine(float value) throws IOException {
		writeLine(String.valueOf(value));
	}

	public void writeLine(double value) throws IOException {
		writeLine(String.valueOf(value));
	}

	@Override
	public String toString() {
		if (file != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date lastModifiedDate = new Date(file.lastModified());
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("CSV ");
			stringBuilder.append("File [").append(file.getPath()).append("], ");
			stringBuilder.append("last modfied at [").append(dateFormat.format(lastModifiedDate) + "], ");
			stringBuilder.append("size [").append(file.length()).append(" bytes].");
			return stringBuilder.toString();
		} else {
			return "CSV File not opened yet.";
		}
	}
}