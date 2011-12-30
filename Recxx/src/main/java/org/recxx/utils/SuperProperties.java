package org.recxx.utils;

import java.util.Properties;

@SuppressWarnings("serial")
public class SuperProperties extends Properties {
	
	private static final String ENV_VARIABLE_START = "${";
	private static final String ENV_VARIABLE_END = "}";

	@Override
	public String getProperty(String key) {
		
		String property = super.getProperty(key);
		while (property != null && property.contains(ENV_VARIABLE_START) && property.contains(ENV_VARIABLE_END)) {
			property = replaceSystemProperty(property);
		}
		return property;
			
	}

	private String replaceSystemProperty(String property) {
		String systemPropertyName = property.substring(property.indexOf(ENV_VARIABLE_START) + 2, property.indexOf(ENV_VARIABLE_END));
			String systemPropertyRequired = System.getProperty(systemPropertyName);
			if (systemPropertyRequired == null) throw new RuntimeException("Property " + systemPropertyName + " not set, please set VM Arguments with -D" + systemPropertyName + "=xxxxxxxx");
			return property.replace(ENV_VARIABLE_START + systemPropertyName + ENV_VARIABLE_END, systemPropertyRequired);
	}
	
}
