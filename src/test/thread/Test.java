package test.thread;

public class Test {
	public static void main(String[] args){
		
		for (int i = 0; i < 10 ; i++) {
			new Thread(new MainThread(i + "")).start();
		}
	
	}
}

