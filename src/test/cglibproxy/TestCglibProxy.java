package test.cglibproxy;

public class TestCglibProxy {
	public static void main(String[] args){
		
		CglibProxy proxy = new CglibProxy();
		BookService book = (BookService)proxy.getInstance(new BookService());
		System.out.println(book.addBook());
	}
}
