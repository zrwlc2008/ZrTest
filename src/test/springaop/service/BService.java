package test.springaop.service;

public class BService {
	
	public void barB(String _msg, int _type) {
		System.out.println("BServiceImpl.barB(msg:" + _msg + " type:" + _type
				+ ")");
		if (_type == 1)
			throw new IllegalArgumentException("�����쳣");
	}

	public void fooB() {
		System.out.println("BServiceImpl.fooB()");
	}
}
