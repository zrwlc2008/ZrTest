package test.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;

//intercept = 拦截
public class CglibProxy implements net.sf.cglib.proxy.MethodInterceptor{
	
	/**
	 * 创建一个target的子类
	 * @param target
	 * @return
	 */
	public Object getInstance(Object target){
		//enhance = 增强
		net.sf.cglib.proxy.Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		
		System.out.println("1111111111");
		
		//System.out.println(obj);
		System.out.println(method);
		
		//对象调用父类的方法，调用proxy.invoke，则会死循环
		//Object result = method.invoke(obj, args);
		Object result = proxy.invokeSuper(obj, args);
		
		System.out.println("2222222222222222");
		
		return result ;
		
	}

}
