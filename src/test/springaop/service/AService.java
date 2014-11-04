package test.springaop.service;

public class AService {
	
	public void barA(String a,String b) {
		System.out.println("AServiceImpl.barA()");
	}

	public void fooA(String _msg) {
		System.out.println("AServiceImpl.fooA(msg:" + _msg + ")");
	}
}
