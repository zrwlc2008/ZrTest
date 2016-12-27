package test.reflect;

public class ReflectProxy implements java.lang.reflect.InvocationHandler{
	
	private Object target ;
	
	public Object getInstance(Object target){
		
		this.target = target; 
		
		System.out.println("getInstance::ClassLoader: " + target.getClass().getClassLoader());
		System.out.println("getInstance::interface: " + target.getClass().getInterfaces());
		
		return java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);
		
	}
	
	@Override
	public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
			throws Throwable {
		//System.out.println("------------invoke-------------------");
		System.out.println("invoke::method: " + method.getName());
		
		Object result =  method.invoke(target, args);
		
		return result ;
		
	}

}
