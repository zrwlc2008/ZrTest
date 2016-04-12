package test.random;

import java.util.Random;

public class RandomTest {
	
	public static void main(String args[]) {
		long startTime;
		long endTime;
		int times = 8000000;
		
		startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			int a = (int) (Math.random() * 5);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Math.random(): " + (endTime - startTime));
		
		Random rand = new Random();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			int a = rand.nextInt(5);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Random.nextInt(): " + (endTime - startTime));
		
		/*
		 * ��ӡ�����
		 * Math.random(): 191  
		 * Random.nextInt(): 97
		 * �ɴ˵�֪��Random.nextInt()��Ч�ʱ�Math.random()�ߺܶ�
		 * */
	}
}
