package test.refactoring.chapter1.version142;

public class Client {
	public static void main(String[] args) {
		
		Movie m1 = new Movie("狮子王", Movie.CHILDRENS);
		Movie m2 = new Movie("黑客帝国", Movie.REGULAR);
		Movie m3 = new Movie("心花路放", Movie.NEW_RELEASE);
		
		Customer customer = new Customer("张睿");
		
		Rental r1 = new Rental(m1, 4);//儿童片3天内1.5元,超过3天每天1.5元  + 积1分
		Rental r2 = new Rental(m2, 3);//普通片2天内2元,超过2天每天1.5元 + 积1分
		Rental r3 = new Rental(m3, 3);//新片每天3元 + 积1分，如果超过2天则再积1分
		
		customer.addRental(r1);
		customer.addRental(r2);
		customer.addRental(r3);
		
		System.out.println(customer.statment());
	}
}
