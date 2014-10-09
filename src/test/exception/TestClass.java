package test.exception;

public class TestClass {
	
	public static void test1() throws Exception{
		try {
			Object o = null ;
			o.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage()) ;
		}
	}
	
	public static void test2() throws Exception{
		
		try {
			test1();
		} catch (Exception e) {
			throw new Exception(e.getMessage()) ;
		}
		
	}
	
	public static void main(String[] args){
	
		try {
			TestClass.test2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
