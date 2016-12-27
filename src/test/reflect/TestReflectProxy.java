package test.reflect;

public class TestReflectProxy {
	public static void main(String[] args){
		
		ReflectProxy proxy = new ReflectProxy();
		IBookService book = (IBookService)proxy.getInstance(new BookService());
		System.out.println(book.addBook());
	}
}
