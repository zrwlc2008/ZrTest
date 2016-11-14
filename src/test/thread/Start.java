package test.thread;

public class Start {
	public static void main(String[] args){
		//模拟多个线程共享一个对象，并同步访问对象状态
		Service service = new Service();
		for (int i = 0; i < 10 ; i++) {
			new Thread(new MainTask(service)).start();
		}
	}
}

