package test.springaop.service;

public class BService {
	
	public void barB() {
		System.out.println("BServiceImpl.barB()");
		//throw new IllegalArgumentException("�����쳣");
	}

	public void fooB() {
		System.out.println("BServiceImpl.fooB()");
	}
}
