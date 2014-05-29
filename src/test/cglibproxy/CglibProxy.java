package test.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;

//intercept = ����
public class CglibProxy implements net.sf.cglib.proxy.MethodInterceptor{
	
	/**
	 * ����һ��target������
	 * @param target
	 * @return
	 */
	public Object getInstance(Object target){
		//enhance = ��ǿ
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
		
		//������ø���ķ���������proxy.invoke�������ѭ��
		//Object result = method.invoke(obj, args);
		Object result = proxy.invokeSuper(obj, args);
		
		System.out.println("2222222222222222");
		
		return result ;
		
	}

}
