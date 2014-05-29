/**
 * 
 * @author Administrator
 *
 */

class ThreadTest implements Runnable {

	@Override
	public void run() {
		
		System.out.println("before sleep");
		try {
			Thread.sleep(5000);
			System.out.println("aaaaaaaaaaaaaaaaaa");
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName());
			System.out.println("after interrupt");
		}
		
		Thread.currentThread().interrupt();

		try {
			Thread.sleep(5000);
			
			System.out.println("bbbbbbbbbbbbbbbbbbbb");
		} catch (InterruptedException e) {
			// e.printStackTrace();
			System.out.println(Thread.currentThread().getName());
			//Thread.currentThread().interrupt();
			System.out.println("after interrupt");
		}
	}

}

public class TestThreadInterrupt {

	public static void main(String[] args) {

		Thread t = new Thread(new ThreadTest(), "thread-1");
		t.start();

		System.out.println("t.isInterrupted():" + Thread.currentThread().isInterrupted());
		//t.interrupt();
		//System.out.println("t.isInterrupted():" + t.isInterrupted());
	}
}