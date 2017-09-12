package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.nio.file.Path;
import java.util.List;

public interface FileHandler {
	public void process(Path file);

	public List<Object[]> getResults();

	public void clearResults();

}
