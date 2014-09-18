package test.thread;

import java.util.Map.Entry;

public class ProcessThread implements Runnable{
	
	private Entry<Integer,String> entry ;
	
	public ProcessThread(Entry<Integer,String> entry){
		this.entry = entry ;
	}
	
	@Override
	public void run() {
		
		String threadName = Thread.currentThread().getName() ;
		
		int time = (int)(Math.random() * 10);
		
		//synchronized(this){
			
			DataBean.occupy(threadName, entry.getKey(), time);
			
		//}0
		
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Ïß³Ì" + threadName + "½áÊø");
		
	}

}
