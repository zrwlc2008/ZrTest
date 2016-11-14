package test.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

	public static void main2222(String[] args) {

		/*
		 * Executors类，提供了一系列工厂方法用于创先线程池，返回的线程池都实现了ExecutorService接口。
		 */
		// 1. 创建固定数目线程的线程池。它创建预定义数量的线程，并不允许线程数量超过这个预定义值。这意味着，如果所有的线程都被使用的话，提交的命令将会被放到一个队列中等待
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		// 2. 创建一个可缓存的线程池
		// 调用execute将重用以前构造的线程（如果线程可用）。
		// 如果现有线程没有可用的，则创建一个新线程并添加到池中。
		// 终止并从缓存中移除那些已有 60秒钟未被使用的线程
		// ExecutorService executorService = Executors.newCachedThreadPool();

		// 3. 创建一个单线程化的Executor。
		// ExecutorService executorService = Executors.newSingleThreadExecutor()

		// 4. 创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类
		// ScheduledExecutorService executorService =
		// Executors.newScheduledThreadPool(int corePoolSize)

		executorService.execute(new MyThread());

		// MyThread t1 = new MyThread();
		//
		// executor.execute(t1);
		//
		// MyThread t2 = new MyThread();
		//
		// executor.execute(t2);
		//
		// MyThread t3 = new MyThread();
		//
		// executor.execute(t3);

		System.out.println("aaaaaaaaaaaaaa");

	}

	public static void main(String args[]) {
		Random random = new Random();
		// 建立一个容量为3的固定尺寸的线程池
		ExecutorService executor = Executors.newFixedThreadPool(3);
		// 判断可是线程池可以结束
		int waitTime = 5000;
		for (int i = 0; i < 10; i++) {
			String name = "--线程 " + i;
			int time = random.nextInt(1000);
			waitTime += time;
			Runnable runner = new ExecutorThread(name, waitTime);
			System.out.println("增加: " + name + " / " + time);
			executor.execute(runner);
		}
		try {
			Thread.sleep(waitTime);
			executor.shutdown();
			executor.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
		} catch (InterruptedException ignored) {
		}
	}

}

class ExecutorThread implements Runnable {
	private final String name;
	private final int delay;

	public ExecutorThread(String name, int delay) {
		this.name = name;
		this.delay = delay;
	}

	public void run() {
		System.out.println("启动: " + name);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ignored) {
		}
		System.out.println("完成: " + name);
	}
}
