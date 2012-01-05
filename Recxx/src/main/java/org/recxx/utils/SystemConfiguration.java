package org.recxx.utils;

public class SystemConfiguration {

	public static final String ENV_VARIABLE_START = "${";
	public static final String ENV_VARIABLE_END = "}";

	public static String replaceSystemProperties(String property) {
		while (property != null && property.contains(SystemConfiguration.ENV_VARIABLE_START) && property.contains(SystemConfiguration.ENV_VARIABLE_END)) {
			property = replaceSystemProperty(property);
		}
		return property;
	}

	private static String replaceSystemProperty(String property) {
		String systemPropertyName = property.substring(property.indexOf(ENV_VARIABLE_START) + 2, property.indexOf(ENV_VARIABLE_END));
		String systemPropertyRequired = System.getProperty(systemPropertyName);
		if (systemPropertyRequired == null) throw new RuntimeException("Property " + systemPropertyName + " not set, please set VM Arguments with -D" + systemPropertyName + "=xxxxxxxx");
		return property.replace(ENV_VARIABLE_START + systemPropertyName + ENV_VARIABLE_END, systemPropertyRequired);
	}
	
}
