package java8.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class FilterMapByKeys {

	public static void filterMapByKeys() {
		Map<Integer, String> hmap = new HashMap<>();
		hmap.put(11, "Apple");
		hmap.put(22, "Orange");
		hmap.put(33, "Kiwi");
		hmap.put(44, "Banana");

		Map<Integer, String> resultMap = hmap.entrySet().stream().filter(entry -> entry.getKey() <= 22)
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		System.out.println(resultMap);
		System.out.println(resultMap);
		System.out.println(resultMap);
	}

}

class FilterMapByValuess{
	public static void filterMapByValues(String[] args) {
		Map<Integer,String> hmap = new HashMap<>();
		hmap.put(11, "Apple");
		hmap.put(22, "Orange");
		hmap.put(33,"Kiwi");
		hmap.put(44, "Banana");
		Map<Integer,String> result = hmap.entrySet().stream()
						.filter(entry->entry.getValue().equalsIgnoreCase("Orange"))
						.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		System.out.println(result);
	}
	
}



public class FilterMapByKeysValues {
	public static void main(String[] args) {
		Map<Integer, String> hmap = new HashMap<>();
		hmap.put(11, "Apple");
		hmap.put(22, "Orange");
		hmap.put(33, "Kiwi");
		hmap.put(44, "Banana");
		Map<Integer,String> result = hmap.entrySet().stream().filter(entry -> entry.getKey() < 33)
				.filter(entry -> entry.getValue().equalsIgnoreCase("Apple"))
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
		System.out.println(result);
	}
}
