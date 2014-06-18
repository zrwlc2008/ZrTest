package test.data_structure;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class TestDataStructure {
	
	public static void testQueue(){
		try {
			ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(6);
			queue.put("1");
			queue.put("2");
			queue.put("3");
			queue.put("4");
			queue.put("5");
			queue.put("6");
			
			queue.poll();
			queue.poll();
			
			queue.put("7");
			queue.put("8");
			
			for (Iterator iterator = queue.iterator(); iterator.hasNext();) {
				String s = (String) iterator.next();
				System.out.println(s);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		testQueue();
	}
}
