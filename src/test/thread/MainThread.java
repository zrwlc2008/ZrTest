package test.thread;

import java.util.HashMap;
import java.util.Map.Entry;

public class MainThread implements Runnable{

	@Override
	public void run() {
		
		while(true){
			System.out.println("���߳̿�ʼ��ȡ����");
			
			DataBean.init();
			
			HashMap<Integer,String> map = DataBean.getMap();
			
			for (Entry<Integer,String> entry : map.entrySet()) {
				
				new Thread(new ProcessThread(entry), entry.getKey()+"").start();
				
			}
			
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
