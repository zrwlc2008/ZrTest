package test.thread;

public class Start {
	public static void main(String[] args){
		//ģ�����̹߳���һ�����󣬲�ͬ�����ʶ���״̬
		Service service = new Service();
		for (int i = 0; i < 10 ; i++) {
			new Thread(new MainTask(service)).start();
		}
	}
}

