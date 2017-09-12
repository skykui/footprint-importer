package sg.asia21at.webdev.footprintimporter.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.postgis.LinearRing;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgis.Polygon;

import sg.asia21at.webdev.footprintimporter.entity.Scene;
import sg.asia21at.webdev.footprintimporter.enums.SridEnum;
import sg.asia21at.webdev.footprintimporter.log.LoggerCreator;

public class SceneDirector {

	private SceneBuilder builder;

	public SceneDirector(SceneBuilder builder) {
		this.builder = builder;
	}

	public List<Scene> constructFromList(List<Object[]> strList) {
		// Avoid repeat scenes
		Map<String, Scene> sceneMap = new HashMap<>();

		for (Object[] row : strList) {
			String sceneId = "D" + row[0] + row[1] + row[2];
			if (!sceneMap.containsKey(sceneId)) {
				builder.newScene();
				builder.satellite((int) row[0]);
				builder.stripId((String) row[1]);
				builder.sceneNum((int) row[2]);
				builder.startTime((Date) row[3]);
				builder.lat((double) row[4]);
				builder.lon((double) row[5]);
				builder.cloudCover((double) row[6]);
				builder.roll((double) row[7]);
				builder.pitch((double) row[8]);
				builder.geom(buildPolygon(row, 9, 17)); // index 17 is exclusive
				builder.endTime((Date) row[17]);
				builder.folderName((String) row[18]);

				
				

				sceneMap.put(sceneId, builder.build());
			} else {
				LoggerCreator.getLogger().debug("Repeat scene found:" + sceneId);
			}
		}
		return new ArrayList<Scene>(sceneMap.values());
	}
	
	private PGgeometry buildPolygon(Object[] row, int start, int end){
		// Close ring including first point
		int len = (end-start)/2+1;
		Point[] pointList = new Point[len];
		for(int i=0; i+1<end-start; i+=2){
			// mind the order: ul, ur, lr, ll, ul. Otherwise, create invalid polygon
			// Long = x, Lat = y
			pointList[i/2] = new Point((double) row[i+start], (double) row[i+start+1]);
		}
		pointList[len-1] = new Point((double) row[start], (double) row[start+1]);
		
		LinearRing[] rings = new LinearRing[]{new LinearRing(pointList)};
		Polygon polygon = new Polygon(rings);
		polygon.setSrid(SridEnum.WGS84.getSrid());
		PGgeometry pGgeometry = new PGgeometry(polygon);
		
		return pGgeometry;
	}

}
