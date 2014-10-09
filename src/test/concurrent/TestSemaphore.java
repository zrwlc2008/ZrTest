package test.concurrent;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long s = new Date().getTime();

		int initPermit = 5 ;
		
		Semaphore semaphore = new Semaphore(initPermit);

		try {

			System.out.println("tryAcquire");
			
			//��һ���������С�ڵ���`initPermit������ֱ��ͨ����
			semaphore.tryAcquire(1, 5, TimeUnit.SECONDS);

			//System.out.println("acquire");
			//semaphore.acquire();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long e = new Date().getTime();

		System.out.println("finish " + (e - s) / 1000 + "s");
	}

}
