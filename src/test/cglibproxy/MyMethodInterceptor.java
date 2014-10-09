package test.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;

//intercept = ����
public class MyMethodInterceptor implements net.sf.cglib.proxy.MethodInterceptor{
	
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
		//Ϊʲô��ӡobj�ͻ���ѭ��?
		System.out.println("CglibProxy.intercept obj=" + obj.getClass());
		System.out.println("CglibProxy.intercept method=" + method);
		System.out.println("CglibProxy.intercept proxy.getSuperName()=" + proxy.getSuperName());
		
		//������ø���ķ���������proxy.invoke�������ѭ��
		//Object result = method.invoke(obj, args);
		Object result = proxy.invokeSuper(obj, args);
		
		System.out.println("CglibProxy.intercept result=" + result);
		
		return result ;
		
	}

}
