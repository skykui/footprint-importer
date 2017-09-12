package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.nio.file.Path;
import java.util.List;

public interface Scanner {
	public List<Object[]> scan(Path dir);
}
