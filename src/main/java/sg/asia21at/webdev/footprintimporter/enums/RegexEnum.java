package sg.asia21at.webdev.footprintimporter.enums;

import java.util.regex.Pattern;

public enum RegexEnum {
	PANMETA("^(TRIPLESAT_([123])_PAN_(?:L1_)?((?:19|20)\\d\\d(?:0[1-9]|1[012])(?:0[1-9]|[12][0-9]|3[01])\\d{6})_([0-9A-F]{6}VI))_(\\d{3})_meta(?:\\.)xml$"), 
	IMIDIR("^D([123])([0-9A-F]{6})VI_(\\d{3})_(\\d{3})$"), 
	L1DIR("^(TRIPLESAT_([123])_PMS_L1_((?:19|20)\\d\\d(?:0[1-9]|1[012])(?:0[1-9]|[12][0-9]|3[01])\\d{6})_([0-9A-F]{6}VI))_(\\d{3})$")
	;

	private String regex;

	private RegexEnum(String regex) {

		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}
	
	public Pattern getPattern() {
		return Pattern.compile(regex);
	}

}
