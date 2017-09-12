package sg.asia21at.webdev.footprintimporter.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileHandlerUtil {
	private FileHandlerUtil() {
	}
	public enum result {
		missingList, extraList
	}

	public static Map<result, List<String>> checkCompletion(final File[] list, final Map<String, Boolean> checkMap, final Set<String> addOnSet) {
		List<String> missingList = new ArrayList<>();
		List<String> extraList = new ArrayList<>();
		Map<String, Boolean> resultMap = new HashMap<>(checkMap);
		for (File file : list) {
			if (null == resultMap.replace(file.getName(), true)) {
				if (!addOnSet.contains(file.getName())) {
					extraList.add(file.getName());
				}
			}
		}
		for (Map.Entry<String, Boolean> entry : resultMap.entrySet()) {
			if (!entry.getValue()) {
				missingList.add(entry.getKey());
			}

		}
		Map<result, List<String>> map = new HashMap<result, List<String>>();		
		map.put(result.missingList, missingList);
		map.put(result.extraList, extraList);
		return map;
		
	}
	
	public static Map<result, List<String>> checkCompletion(File[] list, Map<String, Boolean> checkMap) {
		return checkCompletion(list, checkMap, new HashSet<>());
	}


}
