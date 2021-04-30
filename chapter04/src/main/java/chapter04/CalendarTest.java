package chapter04;

import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		printDate(cal);
		
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(2010, 10, 10);
		cal.add(Calendar.DATE,100);
		printDate(cal);
	}

	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"};
		int year =cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		// 요일 [1(일)~7(토)]
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int hours = cal.get(Calendar.HOUR);
		int minutes = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);
		
		System.out.println(year+"년 "+
				month+"월 "+
				date+"일 "+
				DAYS[day-1] + "요일 " +
				hours+"시"+
				minutes+"분"+
				seconds+"초");
	}
//final 클래스>상속x 메소드>오버라이드x 변수>대입x
}
