package test.calendar;

import java.util.Calendar;

public class TestCalendar {
	/**
	 * roll的添加方式是循环滚动，比如增加到了本月最后一天，又会回到本月1号
	 */
	public static void testRoll(){
		Calendar cl = Calendar.getInstance() ;
		System.out.println(cl.get(Calendar.YEAR) + "-" +  (cl.get(Calendar.MONTH) + 1)  + "-" + cl.get(Calendar.DAY_OF_MONTH));
		
		cl.set(Calendar.MONTH, Calendar.FEBRUARY);
		for (int i = 0; i < 30 ; i++) {
			
			//cl.roll(Calendar.DATE, 1);
			cl.roll(Calendar.DATE, true);
			System.out.println(cl.get(Calendar.YEAR) + "-" +  (cl.get(Calendar.MONTH) + 1)  + "-" + cl.get(Calendar.DAY_OF_MONTH));
		}
	}
	
	/**
	 * add是按日历一直向下加下去
	 */
	public static void testAdd(){
		Calendar cl = Calendar.getInstance() ;
		System.out.println(cl.get(Calendar.YEAR) + "-" +  (cl.get(Calendar.MONTH) + 1)  + "-" + cl.get(Calendar.DAY_OF_MONTH));
		
		for (int i = 0; i < 30 ; i++) {
			
			cl.add(Calendar.DATE, 1);
			System.out.println(cl.get(Calendar.YEAR) + "-" +  (cl.get(Calendar.MONTH) + 1)  + "-" + cl.get(Calendar.DAY_OF_MONTH));
		}
	}
	
	public static void main(String[] args){
		testRoll();
	}
}
