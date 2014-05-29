package test.annotation;

import test.annotation.MyAnnotation.Sex;

@MyAnnotation(username="aaa",sex=Sex.WOMAN)
public class TestAnnotation {

	public static void main(String[] args){
		
		MyAnnotation myAnno = (MyAnnotation)TestAnnotation.class.getAnnotation(MyAnnotation.class);
		System.out.println(myAnno.username());
		System.out.println(myAnno.sex());
	}
	
}
