package test.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorTest {
	// ����һ���ɻ�����̳߳أ�
	// ����execute��������ǰ������̣߳�����߳̿��ã�����������߳�û�п��õģ��򴴽�һ�����̲߳���ӵ����С���ֹ���ӻ������Ƴ���Щ���� 60
	// ����δ��ʹ�õ��߳�
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

