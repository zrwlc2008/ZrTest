package test.redis;


public class TestJedisConn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JedisConn conn = JedisPoolUtil.getConnection();

//		conn.set("hello", "world");
//		System.out.println(conn.get("hello"));
//
//		conn.set("num", "1");
//		// 变为2
//		conn.increase("num");
//		System.out.println(conn.get("num"));
//
//		// 变为4
//		conn.increase("num", 2);
//		System.out.println(conn.get("num"));
//
//		// 变为3
//		conn.decrease("num");
//		System.out.println(conn.get("num"));
//
//		// 变为1
//		conn.decrease("num", 2);
//		System.out.println(conn.get("num"));
//
//		//conn.set("hello6", "world6");
//		System.out.println(conn.get("hello5"));
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("1", "11111");
//		map.put("2", "2222");
//		map.put("3", "33333");
//		map.put("4", "444444");
//		conn.setMap("map", map);
//		
//		Map<String, String> map2 = conn.getMap("map");
//		System.out.println(map2);
//		
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("aaaaaa");
//		list.add("bbbbb");
//		list.add("cccccc");
//		conn.setList("list", list);
//		
//		List<String> list2 = conn.getList("list");
//		for (String string : list2) {
//			System.out.println(string);
//		}
		
		//System.out.println(conn.get("hello4"));
		
		conn.set("num", "0");
		
		for (int i = 0; i < 1000 ; i++) {
			
			Thread t = new Thread(new TestThread(), i + "");
			t.start();
			
		}
	}

}
