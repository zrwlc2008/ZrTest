package test.thread;

public class MainTask implements Runnable {

	private Service service ;
	
	public MainTask(Service service){
		this.service = service ;
	}
	
	@Override
	public void run() {
		System.out.println("线程" + Thread.currentThread().getId() + "运行");
		try {
			service.sleep(Thread.currentThread());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
