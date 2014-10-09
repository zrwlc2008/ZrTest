package test.cglibproxy;

public class TestCglibProxy {
	public static void main(String[] args){
		
		MyMethodInterceptor proxy = new MyMethodInterceptor();
		BookService book = (BookService)proxy.getInstance(new BookService());
		System.out.println(book.addBook());
	}
}
