package sg.asia21at.webdev.footprintimporter.util;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import sg.asia21at.webdev.footprintimporter.log.LoggerCreator;


public class ListFilesUtil {

	/**
	 * List all the files and folders from a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	
	private ListFilesUtil(){
		
	}
	public static File[] listFilesAndFolders(String directoryName) {

		File directory = new File(directoryName);
		// get all the files from a directory

		return listFilesAndFolders(directory);
	}

	public static File[] listFilesAndFolders(Path directoryName) {

		File directory = directoryName.toFile();
		// get all the files from a directory

		return listFilesAndFolders(directory);
	}

	public static File[] listFilesAndFolders(File directory) {

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			LoggerCreator.getLogger().debug(file.getName());
		}
		return fList;
	}

	/**
	 * List all the files under a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public ArrayList<File> listFiles(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		return listFiles(directory);
	}

	public ArrayList<File> listFiles(Path directoryName) {
		File directory = directoryName.toFile();
		// get all the files from a directory
		return listFiles(directory);
	}

	public ArrayList<File> listFiles(File directory) {
		// get all the files from a directory
		File[] fList = directory.listFiles();
		ArrayList<File> fileList = new ArrayList<>();
		for (File file : fList) {
			if (file.isFile()) {
				System.out.println("Found: " + file.getName());
				fileList.add(file);
			}
		}
		return fileList;
	}

	/**
	 * List all the folder under a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public ArrayList<File> listFolders(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		return listFolders(directory);

	}

	public ArrayList<File> listFolders(Path directoryName) {
		File directory = directoryName.toFile();
		// get all the files from a directory
		return listFolders(directory);

	}

	public ArrayList<File> listFolders(File directory) {

		// get all the files from a directory
		File[] fList = directory.listFiles();
		ArrayList<File> dirList = new ArrayList<>();
		for (File file : fList) {
			if (file.isDirectory()) {
				System.out.println("Found: " + file.getAbsolutePath());
				dirList.add(file);
			}
		}
		return dirList;
	}

	/**
	 * List all files from a directory and its subdirectories
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public ArrayList<File> listFilesAndFilesSubDirectories(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		return listFilesAndFilesSubDirectories(directory);
	}

	public ArrayList<File> listFilesAndFilesSubDirectories(File directory) {
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				System.out.println(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories("Found: " + file.getAbsolutePath());
			}
		}
		return new ArrayList<File>(Arrays.asList(fList));
	}


	public static void main(String[] args) {
		ListFilesUtil listFilesUtil = new ListFilesUtil();
		final String directoryLinuxMac = "Z:\\Java8\\IMIFolderSplitter\\D2000ACBVI_001_034";
		// Windows directory example
		// final String directoryWindows ="C://test";
		listFilesUtil.listFiles(directoryLinuxMac);
		//		listFilesUtil.listFilesAndFilesSubDirectories(directoryLinuxMac);
	}
}
