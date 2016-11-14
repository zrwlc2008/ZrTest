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
		System.out.println("�߳�" + t.getId() + "  cnt=" + cnt);
		int r = d.nextInt(10);
		System.out.println("�߳�" + t.getId() + "����" + r + "�� ");
		Thread.sleep(r*1000);
	}
}
