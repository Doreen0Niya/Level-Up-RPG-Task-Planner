package utinity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate {

	public static SimpleDateFormat dateFormat= new SimpleDateFormat("E, 'at' a h:m, M-d-y,");
	public static String sUserDateFormat = "M-d-y h:m";
	public static SimpleDateFormat userDateFormat = new SimpleDateFormat(sUserDateFormat);
	public static Calendar calendar = Calendar.getInstance();
	public static void main(String[] args) {
		System.out.println(calendar.getActualMaximum(calendar.DATE));
	}
}
