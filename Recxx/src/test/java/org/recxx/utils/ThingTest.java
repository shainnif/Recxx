package org.recxx.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class ThingTest {

	@Test
	public void test() {
		
		try {

			Set<String> unique = new TreeSet<String>();
			BufferedReader br = openFile("C:\\tempstuff\\CDR_data_extract_for_Erebus.csv");
			
			String line = br.readLine();

			while (line != null) {
				
				String pattern = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
				String[] fields = line.split(pattern);
				
				if (fields.length > 7) {
//					System.out.println(fields[7]);
					unique.add(fields[7]);
				}

				line = br.readLine();
			}
			
			for (String string : unique) {
				System.out.println(string);
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
    private BufferedReader openFile(String filePath) {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(filePath);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }
}
