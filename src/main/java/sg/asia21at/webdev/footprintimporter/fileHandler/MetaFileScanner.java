package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import sg.asia21at.webdev.footprintimporter.enums.RegexEnum;

public class MetaFileScanner implements Scanner{
	String metaReg = RegexEnum.PANMETA.getRegex();

	public List<Object[]> scan(Path dir){
		MetaFileHandler fileHandler = new MetaFileHandler();
		FindFileVisitor findFileVisitor = new FindFileVisitor(metaReg, fileHandler);
		try {
			Files.walkFileTree(dir, findFileVisitor);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileHandler.getResults();
	}
	
}
