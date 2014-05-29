package test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//不定义retention的话，JVM不会加载注解具体的配置信息
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	String username() ; 
	public enum Sex { MAN, WOMAN };
	Sex sex() default Sex.MAN ;
}
