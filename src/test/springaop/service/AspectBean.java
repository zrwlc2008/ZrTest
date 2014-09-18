package test.springaop.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AspectBean {


    public void doAfter(JoinPoint jp) {  
        System.out.println("doAfter : "  
                + jp.getTarget().getClass().getName() + "---"  
                + jp.getSignature().getName());  
    }  
  
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        long time = System.currentTimeMillis();  
        Object retVal = pjp.proceed();  
        time = System.currentTimeMillis() - time;  
        System.out.println("doAround : " + time + " ms");  
        return retVal;  
    }  
  
    public void doBefore(JoinPoint jp) {  
        System.out.println("doBefore : "  
                + jp.getTarget().getClass().getName() + "."  
                + jp.getSignature().getName());  
    }  
  
    public void doAfterThrowing(JoinPoint jp, Throwable ex) {  
        System.out.println("doAfterThrowing: " + jp.getTarget().getClass().getName()  
                + "." + jp.getSignature().getName() + " throw exception");  
        System.out.println(ex.getMessage());  
    }  
  
    private void sendEx(String ex) {  
        //TODO 发送短信或邮件提醒  
    }
}
