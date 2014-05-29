package test.jdkproxy;

public class BookService implements BookFacade {

	@Override
	public String addBook() {
		System.out.println("add book");
		return "123" ;
	}
	
	
	
}
