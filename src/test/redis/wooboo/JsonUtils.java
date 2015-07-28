package test.redis.wooboo;

import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;



/**
 *  
 *@Description: json 转化处理工具类
 *@Author: pengkunj
 *@Since:Jan 19, 2012
 *@Version:1.1.0
 */
public class JsonUtils {
	
	/**
	 * 记录log日志
	 */
	private static final Logger logger = Logger.getLogger(JsonUtils.class);
    /** 
     * Object对象转换为JSON格式  
     *  
     * 例如List对象、JavaBean对象、JavaBean对象数组、Map对象、List Map对象 
     * @param object 传入的Object对象 
     * @return object的JSON格式字符串 
     */  
    public static String ObjToJson(Object object) {  
    	 // Jackson方式转换为Json  
		MappingJsonFactory f = new MappingJsonFactory();   
		StringWriter sw = new StringWriter();
    	try {  
            JsonGenerator generator = f.createJsonGenerator(sw);  
            generator.writeObject(object);  
            generator.close();  
  
        } catch (Exception e) {  
        	logger.info("",e);
            return "";  
        }  
        return sw.toString();  
          
    }  
  
    /** 
     * JSON转换为Object对象 
     *  
     * @param jsonString JSON字符串 
     * @param c 要转换成的类的类型 
     * @return Object对象 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonToObject(String jsonString, Class c){  
  
        if (jsonString == null || "".equals(jsonString)) {  
            return null;  
  
        } else {  
  
            // Jackson方式将Json转换为对象  
            MappingJsonFactory f = new MappingJsonFactory();  
            try {  
                JsonParser parser = f.createJsonParser(jsonString);  
                return parser.readValueAs(c);  
  
            } catch (Exception e) {
            	logger.info("", e);
                return null;  
            }  
        }  
    } 
    
}  