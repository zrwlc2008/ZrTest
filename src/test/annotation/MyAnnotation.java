package test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//������retention�Ļ���JVM�������ע������������Ϣ
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	String username() ; 
	Class testClass();
	public enum Sex { MAN, WOMAN };
	Sex sex() default Sex.MAN ;
}
