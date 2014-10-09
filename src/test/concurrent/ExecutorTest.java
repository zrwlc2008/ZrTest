package test.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorTest {
	// 创建一个可缓存的线程池，
	// 调用execute将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程
	private static Executor executor = Executors.newCachedThreadPool();

	public void work() {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("task" + Thread.currentThread().getId() + "----print------" + i);
				}
			}
		};
		//Thread d = new Thread(task);
		//d.start();
		executor.execute(task);
	}

	public static void main(String[] args) {

		ExecutorTest test = new ExecutorTest();

		//for (int i = 0; i < 3; i++) {
			test.work();
		//}

	}
}
