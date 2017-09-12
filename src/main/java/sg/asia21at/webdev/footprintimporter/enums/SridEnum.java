package sg.asia21at.webdev.footprintimporter.enums;

public enum SridEnum {
	WGS84(4326), WEBMERCATOR(3857)
	
	;

	private int srid;

	private SridEnum(int srid) {

		this.srid = srid;
	}

	public int getSrid() {
		return this.srid;
	}
	

}
