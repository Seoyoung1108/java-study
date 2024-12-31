package chapter04;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale aLocale = Locale.getDefault(Locale.Category.FORMAT);
		TimeZone tz = TimeZone.getDefault();
		System.out.println(aLocale+":"+tz);
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		printDate(cal);
		
		cal.set(Calendar.YEAR,2024);
		cal.set(Calendar.MONTH, 11); // 12월 -1
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(2000, 11, 26);
		cal.add(Calendar.DATE, 10000);
		printDate(cal);
	}

	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일","월","화","수","목","금","토"};
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); //0-11 -> +1
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK); // 1-7, 일요일 시작
		int hours = cal.get(Calendar.HOUR);
		int minutes = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);
		
		System.out.println(year+"-"+(month+1)+"-"+date+" "+DAYS[day-1]+"요일 "+hours+":"+minutes+":"+seconds);
	}

}
