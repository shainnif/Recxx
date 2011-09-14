package org.recxx.utils;

import java.util.Properties;

@SuppressWarnings("serial")
public class SuperProperties extends Properties {
	
	private static final String ENV_VARIABLE_START = "${";
	private static final String ENV_VARIABLE_END = "}";

	@Override
	public String getProperty(String key) {
		
		String property = super.getProperty(key);
		if (property != null && property.contains(ENV_VARIABLE_START) && property.contains(ENV_VARIABLE_END)) {
			String unEscapedProperty = property.substring(property.indexOf(ENV_VARIABLE_START) + 2, property.indexOf(ENV_VARIABLE_END));
				String propRequired = System.getProperty(unEscapedProperty);
				if (propRequired == null) throw new RuntimeException("Property " + unEscapedProperty + " not set, please set VM Arguments with -D" + unEscapedProperty + "=xxxxxxxx");
				return property.replace(ENV_VARIABLE_START + unEscapedProperty + ENV_VARIABLE_END, propRequired);
		}
		return property;
			
	}
	
}
