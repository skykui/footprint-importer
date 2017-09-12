package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import sg.asia21at.webdev.footprintimporter.enums.RegexEnum;
import sg.asia21at.webdev.footprintimporter.log.LoggerCreator;
import sg.asia21at.webdev.footprintimporter.util.Convertor;
import sg.asia21at.webdev.footprintimporter.util.FileHandlerUtil;
import sg.asia21at.webdev.footprintimporter.util.ListFilesUtil;
import sg.asia21at.webdev.footprintimporter.util.XmlReader;

public class Folder1mFileHandler implements FileHandler {
	private final XmlReader xmlReader = new XmlReader();
	private List<Object[]> results = new ArrayList<Object[]>();

	private final String Cloudmask = "Cloudmask";
	private final String MS = "MS";
	private final String PAN = "PAN";
	private final String PreProcessed = "PreProcessed.mark";

	@Override
	public void process(Path dir) {

		Matcher matcher = RegexEnum.L1DIR.getPattern().matcher(dir.getFileName().toString());

		if (!matcher.matches()) {
			return;
		}

		List<String> missingList = new ArrayList<>();
		List<String> extraList = new ArrayList<>();
		checkDir(dir, missingList, extraList);
		addResult(dir, missingList.isEmpty(), extraList.isEmpty());

	}

	private void addResult(Path dir, boolean isComplete, boolean noExtra) {
		String l1DirName = dir.getFileName().toString();
		String meta_pan = l1DirName.replace("_PMS_", "_PAN_") + "_meta.xml";
		try {
			Object[] row = new Object[20];
			xmlReader.openDoc(dir.resolve(PAN).resolve(meta_pan));
			row[0] = getSatFromStr(xmlReader.getString("Satellite_Name").trim()); // int
			row[1] = xmlReader.getString("Acquisition_ID").trim(); // String
			row[2] = getSceneNumFromStr(xmlReader.getString("Scene_ID").trim()); // int
			row[3] = Convertor.getDateFromStr(xmlReader.getString("Begin_Time").trim()); // Date
			row[4] = Double.parseDouble(xmlReader.getString("Central_Lat").trim()); // double
																					// below
			row[5] = Double.parseDouble(xmlReader.getString("Central_Lon").trim());
			row[6] = Double.parseDouble(xmlReader.getString("Cloud_Cover").trim());
			row[7] = Double.parseDouble(xmlReader.getString("Roll_Angle").trim());
			row[8] = Double.parseDouble(xmlReader.getString("Pitch_Angle").trim());
			row[9] = Double.parseDouble(xmlReader.getString("UL_Lat").trim()); // double
																				// below
			row[10] = Double.parseDouble(xmlReader.getString("UL_Lon").trim());
			row[11] = Double.parseDouble(xmlReader.getString("UR_Lat").trim()); // double
																				// below
			row[12] = Double.parseDouble(xmlReader.getString("UR_Lon").trim());
			row[13] = Double.parseDouble(xmlReader.getString("LL_Lat").trim()); // double
																				// below
			row[14] = Double.parseDouble(xmlReader.getString("LL_Lon").trim());
			row[15] = Double.parseDouble(xmlReader.getString("LR_Lat").trim()); // double
																				// below
			row[16] = Double.parseDouble(xmlReader.getString("LR_Lon").trim());
			row[17] = Convertor.getDateFromStr(xmlReader.getString("End_Time").trim());
			row[18] = isComplete;
			row[19] = noExtra;
			results.add(row);

		} catch (Exception e) {
			LoggerCreator.getLogger().warn("Fail to retrieve information of: " + l1DirName);
			LoggerCreator.getLogger().warn("Missing meta file: " + meta_pan);
		}
	}

	private void checkDir(Path dir, List<String> missingList, List<String> extraList) {
		String l1DirName = dir.getFileName().toString();
		Map<String, Boolean> checkMap = createDirCheckMap();
		Set<String> addOnSet = new HashSet<>();
		addOnSet.add(PreProcessed);
		File[] list = ListFilesUtil.listFilesAndFolders(dir);

		Map<FileHandlerUtil.result, List<String>> resultMap = FileHandlerUtil.checkCompletion(list, checkMap, addOnSet);
		missingList.addAll(resultMap.get(FileHandlerUtil.result.missingList));
		extraList.addAll(resultMap.get(FileHandlerUtil.result.extraList));

		checkMap = createCloudmaskCheckMap(l1DirName);
		list = ListFilesUtil.listFilesAndFolders(dir.resolve(Cloudmask));
		resultMap = FileHandlerUtil.checkCompletion(list, checkMap);
		missingList.addAll(resultMap.get(FileHandlerUtil.result.missingList));
		extraList.addAll(resultMap.get(FileHandlerUtil.result.extraList));

		checkMap = createMsCheckMap(l1DirName);
		list = ListFilesUtil.listFilesAndFolders(dir.resolve(MS));
		resultMap = FileHandlerUtil.checkCompletion(list, checkMap);
		missingList.addAll(resultMap.get(FileHandlerUtil.result.missingList));
		extraList.addAll(resultMap.get(FileHandlerUtil.result.extraList));

		checkMap = createPanCheckMap(l1DirName);
		list = ListFilesUtil.listFilesAndFolders(dir.resolve(PAN));
		resultMap = FileHandlerUtil.checkCompletion(list, checkMap);
		missingList.addAll(resultMap.get(FileHandlerUtil.result.missingList));
		extraList.addAll(resultMap.get(FileHandlerUtil.result.extraList));
		for (String s : missingList) {
			LoggerCreator.getLogger().warn("Missing file in " + dir + ": " + s);
		}
		for (String s : extraList) {
			LoggerCreator.getLogger().warn("Extra file in " + dir + ": " + s);
		}
	}

	private Map<String, Boolean> createCloudmaskCheckMap(String l1DirName) {
		String prefix = l1DirName.replace("_PMS_", "_");
		Map<String, Boolean> myMap = new HashMap<String, Boolean>();
		myMap.put(prefix + ".dbf", false);
		myMap.put(prefix + ".prj", false);
		myMap.put(prefix + ".shp", false);
		myMap.put(prefix + ".shx", false);
		return myMap;
	}

	private Map<String, Boolean> createMsCheckMap(String l1DirName) {
		String prefix = l1DirName.replace("_PMS_", "_MS_");
		Map<String, Boolean> myMap = new HashMap<String, Boolean>();
		myMap.put(prefix + ".tif", false);
		myMap.put(prefix + ".xml", false);
		myMap.put(prefix + "_browser.jpg", false);
		myMap.put(prefix + "_browser.png", false);
		myMap.put(prefix + "_browser.tif", false);
		myMap.put(prefix + "_meta.xml", false);
		myMap.put(prefix + "_rpc.txt", false);
		myMap.put(prefix + "_thumb.jpg", false);
		return myMap;
	}

	private Map<String, Boolean> createPanCheckMap(String l1DirName) {
		String prefix = l1DirName.replace("_PMS_", "_PAN_");
		Map<String, Boolean> myMap = new HashMap<String, Boolean>();
		myMap.put(prefix + ".tif", false);
		myMap.put(prefix + ".xml", false);
		myMap.put(prefix + "_browser.jpg", false);
		myMap.put(prefix + "_browser.png", false);
		myMap.put(prefix + "_browser.tif", false);
		myMap.put(prefix + "_meta.xml", false);
		myMap.put(prefix + "_rpc.txt", false);
		myMap.put(prefix + "_thumb.jpg", false);
		return myMap;
	}

	private Map<String, Boolean> createDirCheckMap() {
		Map<String, Boolean> myMap = new HashMap<String, Boolean>();
		myMap.put("Cloudmask", false);
		myMap.put("MS", false);
		myMap.put("PAN", false);
		return myMap;
	}

	public int getSatFromStr(String s) {
		return Integer.parseInt(s.substring(24, 25));
	}

	public int getSceneNumFromStr(String s) {
		return Integer.parseInt(s.substring(43, 46));
	}

	@Override
	public List<Object[]> getResults() {
		return results;
	}

	@Override
	public void clearResults() {
		results = new ArrayList<Object[]>();

	}

	public void printResults() {
		for (Object[] row : results) {
			for (Object s : row) {
				System.out.print(s + " ");
			}
			System.out.println("");

		}
	}

}
