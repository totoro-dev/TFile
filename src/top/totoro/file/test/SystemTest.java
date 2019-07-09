package top.totoro.file.test;

import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class SystemTest {

	public static void main(String[] args) {
		System.out.println("----------------------------Properties Test----------------------------");
		Set<String> propertySet = new TreeSet<>();
		Properties properties = System.getProperties();
		Set<Object> keySet = properties.keySet();
		for (Object key : keySet) {
			StringBuilder value = new StringBuilder((String)key);
			value.append(": ");
			value.append(properties.getProperty((String)key));
			propertySet.add(value.toString());
		}
		for (String property : propertySet) {
			System.out.println(property);
		}
		System.out.println("----------------------------Properties Test----------------------------");
	}

}
