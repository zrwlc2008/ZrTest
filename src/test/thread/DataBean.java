package test.thread;

import java.util.HashMap;

public class DataBean {

	private static HashMap<Integer, String> map ;

	public static HashMap<Integer, String> getMap() {
		return map;
	}
	
	public static void init(){
		
		map = new HashMap<Integer, String>();
		map.put(1, "yes");
		map.put(2, "yes");
		map.put(3, "yes");
		map.put(4, "yes");
		map.put(5, "yes");
	}

	public static void occupy(String threadName, int id , int time) {
		System.out.println(threadName + "占用了对象" + id + "，需要" + time + "秒");
		map.put(id, "no");
	}

}
