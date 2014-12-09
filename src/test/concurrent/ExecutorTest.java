package test.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorTest {
	// 创建一个可缓存的线程池，
	// 调用execute将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60
	// 秒钟未被使用的线程
	private static Executor executor = Executors.newCachedThreadPool();

	public void execute(Runnable command) {
		// Thread d = new Thread(task);
		// d.start();
		executor.execute(command);
	}

	public static void main(String[] args) {

		ExecutorTest test = new ExecutorTest();

		MyThread t1 = new MyThread();

		test.execute(t1);

		MyThread t2 = new MyThread();

		test.execute(t2);

		MyThread t3 = new MyThread();

		test.execute(t3);
		
		System.out.println("aaaaaaaaaaaaaa");

	}

}

