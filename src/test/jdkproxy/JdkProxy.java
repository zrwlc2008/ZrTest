package test.jdkproxy;

public class JdkProxy implements java.lang.reflect.InvocationHandler{
	
	private Object target ;
	
	public Object getInstance(Object target){
		
		System.out.println("------------getInstance-------------------");
		
		this.target = target; 
		
		System.out.println(target.getClass().getClassLoader());
		System.out.println("interface: " + target.getClass().getInterfaces());
		
		return java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);
		
	}
	
	@Override
	public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
			throws Throwable {
		//System.out.println("------------invoke-------------------");
		System.out.println(method);
		
		Object result =  method.invoke(target, args);
		
		System.out.println("222222222222222");
		
		return result ;
		
	}

}
