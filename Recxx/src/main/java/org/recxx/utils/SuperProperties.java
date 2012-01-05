package org.recxx.utils;

import java.util.Properties;

@SuppressWarnings("serial")
public class SuperProperties extends Properties {
	
	@Override
	public String getProperty(String key) {
		
		return SystemConfiguration.replaceSystemProperties(super.getProperty(key));
			
	}
	
}
