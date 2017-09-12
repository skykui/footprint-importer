package sg.asia21at.webdev.footprintimporter.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Convertor {
	private final static String ISO_DATE_TIME_FORMAT = "uuuu-MM-dd'T'HH:mm:ss.SSSSSSX";

	private Convertor() {

	}

	public static Date getDateFromStr(String s) {
		return Date.from(LocalDateTime.parse(s).toInstant(ZoneOffset.UTC));
	}

	public static Date getIsoDateFromStr(String s) {
		return Date.from(ZonedDateTime.parse(s, DateTimeFormatter.ofPattern(ISO_DATE_TIME_FORMAT)).toInstant());
	}
}
