package test.thread;

import java.util.HashMap;
import java.util.Map.Entry;

public class MainThread implements Runnable{
	
	private String s ;
	
	public MainThread(String s){
		this.s = s ;
	}

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getId() + "在运行:" + s);
		
		DataBean.init();
		
//		HashMap<Integer,String> map = DataBean.getMap();
//		
//		for (Entry<Integer,String> entry : map.entrySet()) {
//			
//			//new Thread(new ProcessThread(entry), entry.getKey()+"").start();
//			
//		}
		
		System.out.println(Thread.currentThread().getId() + "运行结束");
	}

}
