package sg.asia21at.webdev.footprintimporter.builder;

import java.util.Date;

import org.postgis.PGgeometry;

import sg.asia21at.webdev.footprintimporter.entity.Scene;
import sg.asia21at.webdev.footprintimporter.util.Convertor;

public class SceneBuilder {
	private Scene scene = new Scene();

	public void newScene() {
		scene = new Scene();
	}

	public Scene build() {
		return this.scene;
	}

	public SceneBuilder satellite(String s) {
		// TripleSat Constellation-2
		satellite(Integer.parseInt(s));
		return this;
	}

	public SceneBuilder satellite(int s) {

		scene.setSatellite(s);
		return this;
	}

	public SceneBuilder stripId(String s) {
		// 000E27
		scene.setStripId(s);
		return this;
	}

	public SceneBuilder sceneNum(String s) {
		// TRIPLESAT_2_PAN_L1_20170521055634_000E27VI_027
		sceneNum(Integer.parseInt(s));
		return this;
	}

	public SceneBuilder sceneNum(int s) {
		// 027
		scene.setSceneNum(s);
		return this;
	}

	public SceneBuilder startTime(String s) {
		// 2017-05-21T05:56:34.368024
		startTime(Convertor.getDateFromStr(s));
		return this;
	}

	public SceneBuilder startTime(Date s) {
		// 2017-05-21T05:56:34.368024
		scene.setStartTime(s);
		return this;
	}
	public SceneBuilder endTime(Date s) {
		// 2017-05-21T05:56:34.368024
		scene.setEndTime(s);
		return this;
	}

	public SceneBuilder lat(String s) {
		lat(Double.parseDouble(s));
		return this;
	}

	public SceneBuilder lat(double s) {
		scene.setLat(s);
		return this;
	}

	public SceneBuilder lon(String s) {
		lon(Double.parseDouble(s));
		return this;
	}

	public SceneBuilder lon(double s) {
		scene.setLon(s);
		return this;
	}

	public SceneBuilder cloudCover(String s) {
		cloudCover(Double.parseDouble(s));
		return this;
	}

	public SceneBuilder cloudCover(double s) {
		scene.setCloudCover(s);
		return this;
	}

	public SceneBuilder roll(String s) {
		roll(Double.parseDouble(s));
		return this;
	}

	public SceneBuilder roll(double s) {
		scene.setRoll(s);
		return this;
	}

	public SceneBuilder pitch(String s) {
		pitch(Double.parseDouble(s));
		return this;
	}

	public SceneBuilder pitch(double s) {
		scene.setPitch(s);
		return this;
	}
	
	public SceneBuilder geom(PGgeometry geom) {
		scene.setGeom(geom);
		return this;
	}
	public SceneBuilder folderName(String s) {
		scene.setFolderName(s);
		return this;
	}
	

}
