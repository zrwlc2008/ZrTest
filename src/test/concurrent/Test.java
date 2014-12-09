package test.concurrent;

public class Test {
	public static void main(String[] args) {

		ThreadPoolExecutorTest test = new ThreadPoolExecutorTest();

		for (int i = 0; i < 10; i++) {

//			Thread t1 = new Thread(new MyThread());
//			t1.start();

			test.execute(new MyThread());
		}

		System.out.println("aaaaaaaaaaaaaa");

	}
}
