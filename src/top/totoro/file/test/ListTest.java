package top.totoro.file.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ListTest {
public static void main(String[] args) {
	Map<String,Integer> map = new HashMap<String, Integer>();
	map.put("1", 11);
	map.put("4", 44);
	map.put("2", 22);
	map.put("3", 33);
	Set<String> keys = map.keySet();
	Iterator<String> ks = keys.iterator();
	while (ks.hasNext()) {
		System.out.println(map.get(ks.next()));
	}
}
}
