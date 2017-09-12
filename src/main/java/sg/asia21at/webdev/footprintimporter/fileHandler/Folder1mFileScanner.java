package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import sg.asia21at.webdev.footprintimporter.enums.RegexEnum;

public class Folder1mFileScanner implements Scanner{

	public List<Object[]> scan(Path dir){
		FileHandler fileHandler = new Folder1mFileHandler();
		CheckDirVisitor visitor = new CheckDirVisitor(RegexEnum.L1DIR.getRegex(), fileHandler);
		try {
			Files.walkFileTree(dir, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileHandler.getResults();
	}
	
}
