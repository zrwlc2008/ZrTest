package test.springaop;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import test.springaop.service.AService;
import test.springaop.service.BService;

public class TestAOP extends AbstractDependencyInjectionSpringContextTests {  
    
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
     * 测试正常调用 
     */  
    public void testCall()  
    {  
        System.out.println("SpringTest JUnit test");  
        aService.barA();  
        bService.fooB();  
        bService.barB();  
    }  
      
    /** 
     * 测试After-Throwing 
     */  
    public void testThrow()  
    {  
        try {  
            bService.barB();  
        } catch (IllegalArgumentException e) {  
              
        }  
    }  
   
} 
