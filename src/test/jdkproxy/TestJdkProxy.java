package test.jdkproxy;

public class TestJdkProxy {
	public static void main(String[] args){
		
		JdkProxy proxy = new JdkProxy();
		BookFacade book = (BookFacade)proxy.getInstance(new BookService());
		System.out.println(book.addBook());
	}
}
