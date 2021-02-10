package es.ewic.frontend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

	public static SimpleDateFormat sdfLong = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	public static SimpleDateFormat sdfBackendDate = new SimpleDateFormat("yyyy-MM-dd");

	public static Calendar parseDateLong(String dateString) {
		return parseDate(dateString, sdfLong);
	}

	public static Calendar parseDate(String dateString, SimpleDateFormat sdf) {
		Calendar cal = new GregorianCalendar();
		try {
			synchronized (sdf) {
				cal.setTime(sdf.parse(dateString));
			}
		} catch (ParseException e) {
			return null;
		}
		return cal;
	}

	public static String formatDateLong(Calendar date) {
		synchronized (sdfLong) {
			return sdfLong.format(date.getTime());
		}
	}

	public static String formatBackendDate(Calendar date) {
		synchronized (sdfBackendDate) {
			return sdfBackendDate.format(date.getTime());
		}
	}

	public static Calendar changeCalendarTimezoneFromUTCToDefault(Calendar utcCalendar) {
		long utcTime = utcCalendar.getTime().getTime();

		long timezoneDefaultTime = utcTime + TimeZone.getDefault().getRawOffset();
		Calendar defaultCalendar = Calendar.getInstance(TimeZone.getDefault());
		defaultCalendar.setTimeInMillis(timezoneDefaultTime);

		return defaultCalendar;
	}

}
