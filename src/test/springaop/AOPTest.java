package test.springaop;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import test.springaop.service.AService;
import test.springaop.service.BService;

public class AOPTest extends AbstractDependencyInjectionSpringContextTests {  
    
    private AService aService;  
      
    private BService bService;  
    
    public void setAService(AService service) {  
        aService = service;  
    }  
      
    public void setBService(BService service) {  
        bService = service;  
    }  
      
    protected String[] getConfigLocations() {  
        String[] configs = new String[] { "/spring.xml"};  
        return configs;  
    }  
      
      
    /** 
     * ������������ 
     */  
    public void testCall()  
    {  
        System.out.println("SpringTest JUnit test");  
        aService.barA();  
        bService.fooB();  
        bService.barB("JUnit test barB",0);  
    }  
      
    /** 
     * ����After-Throwing 
     */  
    public void testThrow()  
    {  
        try {  
            bService.barB("JUnit call barB",1);  
        } catch (IllegalArgumentException e) {  
              
        }  
    }  
   
} 
