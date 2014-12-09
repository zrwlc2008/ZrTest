package test.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	// �������,���߳���Ŀ�ﵽcorePoolSizeʱ.�����������˶���.
	// ������г��ȴﵽqueueSize���򴴽����̡߳����߳������ﵽmaxPoolSize����ܾ��������
	private static final int queueSize = 1;
	private static ArrayBlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(queueSize);

	// ����һ���ɻ�����̳߳أ�
	// ����execute��������ǰ������̣߳�����߳̿��ã�����������߳�û�п��õģ��򴴽�һ�����̲߳���ӵ����С���ֹ���ӻ������Ƴ���Щ���� 60
	// ����δ��ʹ�õ��߳�
	ThreadPoolExecutor taskPool = new ThreadPoolExecutor(5, 10, 0, TimeUnit.SECONDS, taskQueue,
			new ThreadPoolExecutor.CallerRunsPolicy());

	public void execute(Runnable command) {
		taskPool.execute(command);
	}

}
