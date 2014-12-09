package test.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	// 任务队列,当线程数目达到corePoolSize时.将新请求放入此队列.
	// 如果队列长度达到queueSize，则创建新线程。当线程总数达到maxPoolSize，则拒绝放入队列
	private static final int queueSize = 1;
	private static ArrayBlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(queueSize);

	// 创建一个可缓存的线程池，
	// 调用execute将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60
	// 秒钟未被使用的线程
	ThreadPoolExecutor taskPool = new ThreadPoolExecutor(5, 10, 0, TimeUnit.SECONDS, taskQueue,
			new ThreadPoolExecutor.CallerRunsPolicy());

	public void execute(Runnable command) {
		taskPool.execute(command);
	}

}
