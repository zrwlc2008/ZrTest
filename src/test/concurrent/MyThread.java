package test.concurrent;

public class MyThread implements Runnable {
	
	private static int cnt = 0 ;
	
	private static Object obj = new Object();
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized(obj){
			System.out.println("task" + Thread.currentThread().getId() + "----print------" + (++cnt));
		}
	
	}

}
