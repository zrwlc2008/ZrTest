package test.reflect;

public class BookService implements IBookService {

	@Override
	public String addBook() {
		System.out.println("add book");
		return "123" ;
	}
	
	
	
}
