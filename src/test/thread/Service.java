package test.thread;

import java.util.Random;

public class Service {
	
	private int cnt ;
	
	private synchronized void add(){
		cnt++ ;
	}
	
	public int getCnt(){
		return cnt ;
	}
	
	public void sleep(Thread t) throws InterruptedException{
		Random d = new Random();
		add();
		System.out.println("线程" + t.getId() + "  cnt=" + cnt);
		int r = d.nextInt(10);
		System.out.println("线程" + t.getId() + "休眠" + r + "秒 ");
		Thread.sleep(r*1000);
	}
}
