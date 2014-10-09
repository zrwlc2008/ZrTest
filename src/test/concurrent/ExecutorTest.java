package test.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorTest {
	// ����һ���ɻ�����̳߳أ�
	// ����execute��������ǰ������̣߳�����߳̿��ã�����������߳�û�п��õģ��򴴽�һ�����̲߳���ӵ����С���ֹ���ӻ������Ƴ���Щ���� 60 ����δ��ʹ�õ��߳�
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
