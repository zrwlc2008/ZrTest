package test.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

	public static void main2222(String[] args) {

		/*
		 * Executors�࣬�ṩ��һϵ�й����������ڴ����̳߳أ����ص��̳߳ض�ʵ����ExecutorService�ӿڡ�
		 */
		// 1. �����̶���Ŀ�̵߳��̳߳ء�������Ԥ�����������̣߳����������߳������������Ԥ����ֵ������ζ�ţ�������е��̶߳���ʹ�õĻ����ύ������ᱻ�ŵ�һ�������еȴ�
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		// 2. ����һ���ɻ�����̳߳�
		// ����execute��������ǰ������̣߳�����߳̿��ã���
		// ��������߳�û�п��õģ��򴴽�һ�����̲߳���ӵ����С�
		// ��ֹ���ӻ������Ƴ���Щ���� 60����δ��ʹ�õ��߳�
		// ExecutorService executorService = Executors.newCachedThreadPool();

		// 3. ����һ�����̻߳���Executor��
		// ExecutorService executorService = Executors.newSingleThreadExecutor()

		// 4. ����һ��֧�ֶ�ʱ�������Ե�����ִ�е��̳߳أ���������¿��������Timer��
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
		// ����һ������Ϊ3�Ĺ̶��ߴ���̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(3);
		// �жϿ����̳߳ؿ��Խ���
		int waitTime = 5000;
		for (int i = 0; i < 10; i++) {
			String name = "--�߳� " + i;
			int time = random.nextInt(1000);
			waitTime += time;
			Runnable runner = new ExecutorThread(name, waitTime);
			System.out.println("����: " + name + " / " + time);
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
		System.out.println("����: " + name);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ignored) {
		}
		System.out.println("���: " + name);
	}
}
