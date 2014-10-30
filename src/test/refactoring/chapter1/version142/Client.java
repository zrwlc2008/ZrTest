package test.refactoring.chapter1.version142;

public class Client {
	public static void main(String[] args) {
		
		Movie m1 = new Movie("ʨ����", Movie.CHILDRENS);
		Movie m2 = new Movie("�ڿ͵۹�", Movie.REGULAR);
		Movie m3 = new Movie("�Ļ�·��", Movie.NEW_RELEASE);
		
		Customer customer = new Customer("���");
		
		Rental r1 = new Rental(m1, 4);//��ͯƬ3����1.5Ԫ,����3��ÿ��1.5Ԫ  + ��1��
		Rental r2 = new Rental(m2, 3);//��ͨƬ2����2Ԫ,����2��ÿ��1.5Ԫ + ��1��
		Rental r3 = new Rental(m3, 3);//��Ƭÿ��3Ԫ + ��1�֣��������2�����ٻ�1��
		
		customer.addRental(r1);
		customer.addRental(r2);
		customer.addRental(r3);
		
		System.out.println(customer.statment());
	}
}
