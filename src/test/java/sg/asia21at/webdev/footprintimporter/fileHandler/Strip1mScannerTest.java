package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class Strip1mScannerTest {
	Scanner scanner = new MetaFileScanner();

	@Test
	public void testScan() {

		List<Object[]> results = scanner.scan(Paths.get("V:/ToolTest/gndtest/L1_split"));
		for (Object[] row : results) {			
			for (Object s : row) {
				System.out.print(s + " ");
			}
			System.out.println("");

		}
	}

}
