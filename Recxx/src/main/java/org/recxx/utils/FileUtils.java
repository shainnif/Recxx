package org.recxx.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	public static String readFileAsString(File file) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			line = SystemConfiguration.replaceSystemProperties(line);
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}
		br.close();
		return builder.toString();
	}
	
	public static void writeFile(String dataToWrite, File file) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(dataToWrite);
		bw.flush();
		bw.close();
	}
	
}
