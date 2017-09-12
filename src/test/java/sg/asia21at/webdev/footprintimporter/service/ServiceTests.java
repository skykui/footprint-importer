package sg.asia21at.webdev.footprintimporter.service;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgis.PGgeometry;
import org.postgis.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sg.asia21at.webdev.footprintimporter.builder.SceneBuilder;
import sg.asia21at.webdev.footprintimporter.builder.SceneDirector;
import sg.asia21at.webdev.footprintimporter.entity.Scene;
import sg.asia21at.webdev.footprintimporter.fileHandler.MetaFileScanner;
import sg.asia21at.webdev.footprintimporter.fileHandler.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest

//@ComponentScan(basePackages = "sg.asia21at.webdev.footprintimporter.dao")
public class ServiceTests {
	Scanner scanner = new MetaFileScanner();
	String in = "V:/ToolTest/dark_in";
	
	@Autowired
	ISceneService service;
	
	@Test
	public void testDaoInsert() {
		SceneBuilder builder = new SceneBuilder();
		SceneDirector director = new SceneDirector(builder);
		
		List<Object[]> results = scanner.scan(Paths.get(in));
		List<Scene> sceneList = director.constructFromList(results);
		
		service.insertList(sceneList);
		
	}
	
	@Test
	public void testDaoSelect() {
		
		
		int id = 10;
		Scene scene = service.selectById(id);
		
		System.out.println(scene.getFolderName() + " " + ((Polygon)((PGgeometry)scene.getGeom()).getGeometry()).toString() );
		
	}

}
