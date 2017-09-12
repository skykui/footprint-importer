package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.postgis.PGgeometry;
import org.postgis.Polygon;

import sg.asia21at.webdev.footprintimporter.builder.SceneBuilder;
import sg.asia21at.webdev.footprintimporter.builder.SceneDirector;
import sg.asia21at.webdev.footprintimporter.entity.Scene;

public class Strip1mScannerToObjTest {
	Scanner scanner = new MetaFileScanner();
//	String in = "V:/ToolTest/gndtest/L1_split";
	String in = "V:/ToolTest/TRIPLESAT_3_PMS_L1_20170210042749_000BF3VI_010";
	
	@Test
	public void testScan() {

		List<Object[]> results = scanner.scan(Paths.get(in));
		for (Object[] row : results) {			
			for (Object s : row) {
				System.out.print(s + " ");
			}
			System.out.println("");

		}
	}
	@Test
	public void testScanToObject() {
		SceneBuilder builder = new SceneBuilder();
		SceneDirector director = new SceneDirector(builder);
		
		List<Object[]> results = scanner.scan(Paths.get(in));
		List<Scene> sceneList = director.constructFromList(results);
		
		for (Scene s : sceneList) {	
			Polygon polygon = (Polygon)((PGgeometry)s.getGeom()).getGeometry();
			System.out.print(s.getFolderName() + " "+ polygon.toString());
			
			System.out.println("");
			
		}
	}

}
