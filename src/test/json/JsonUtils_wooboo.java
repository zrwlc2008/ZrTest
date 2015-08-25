package test.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.alibaba.fastjson.JSON;

/**
* <p>Title: DSP平台</p>
*
* <p>Description: DSP Platform</p>
*
* <p>Copyright: Copyright (c) 2015.5.28</p>
*
* <p>Company: Wooboo</p>
*
* 类名称：JsonUtils    
* 类描述：    封装JSON对象转换
* 创建人：duanwei    
* 创建时间�?015-5-28 下午5:13:21    
* 修改人：duanwei    
* 修改时间�?015-5-28 下午5:13:21    
* 修改备注�?   
* 
* @author duanwei
* 
* @version 1.0.0
*/
public class JsonUtils_wooboo {


	/**
	 * 
	 * @param json
	 * @param cls
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String objectToJackson(Object json, Class cls)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerSubtypes(cls);
		String reqJson = mapper.writeValueAsString(json);
		return reqJson;
	}

	/**
	 * 
	 * @param json
	 * @param cls
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object jsongToObject(String json, Class cls)
			throws JsonGenerationException, JsonMappingException, IOException {
		Object obj = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		obj = mapper.readValue(json, cls);
		return obj;
	}

	public static Map<String, Object> toMap(String json) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> map = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		map = mapper.readValue(json, Map.class);
		return map;
	}
	
   public static List<Map> toMapList(String json) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Map.class);
        List<Map> lst =  (List<Map>)mapper.readValue(json, javaType);
        return lst;
    }
	
   public static List<Map> toObjectList(String json,Class cls) throws JsonParseException, JsonMappingException, IOException {
       ObjectMapper mapper = new ObjectMapper();
       JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,cls);
       List lst =  (List)mapper.readValue(json, javaType);
       return lst;
   }
   
	public static String toJson(Map<String, String> map) {
		Set<String> keys = map.keySet();
		String key = "";
		String value = "";
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append("{");
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			key = it.next();
			value = map.get(key);
			jsonBuffer.append(key + ":" + value);
			if (it.hasNext()) {
				jsonBuffer.append(",");
			}
		}
		jsonBuffer.append("}");
		return jsonBuffer.toString();
	}

	/**
	 * 
	 * @param json
	 * @param c
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static Object jsonToObject(String json) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		object = JsonUtils_wooboo.jsongToObject(json, Object.class);

		return object;
	}

	/**
	 * 将json字符串转换为map对象
	 * 
	 * @param url
	 * @param object
	 * @return
	 */
	public static HashMap<String, String> jsonToMap(String jsonString) {
		HashMap<String, String> retMap = null;
		if (jsonString == null) {
			return null;
		} else {
			retMap = JSON.parseObject(jsonString, HashMap.class);
		}
		return retMap;
	}

	public static void main(String[] s) throws JsonGenerationException,
			JsonMappingException, IOException {
		// String json="[{\"userId\":'1'},{'userId':'2'}]";
		// List t5=(List)JascksonUtil.jsonToObject(json);
		// Map user1=(Map)t5.get(0);
		// logger.info("user1:{}",user1.get("userId"));
		//String jsonStr = "{'ATTACHAMOUNT':'0','PRODUCTAMOUNT':'5000','UPTRANSEQ':'20120731661159','RETNCODE':'0000','ORDERAMOUNT':'5000','ORDERSEQ':'600102310100003720120924003383','TRANDATE':'20120731','CURTYPE':'RMB','RETNINFO':'0000','ORDERREQTRANSEQ':'201207311022090000000000888942'}";
		//String pm = "[{\"channelid\":3,\"channelname\":\"namessss\",\"pack\":\"dfgdfg\",\"price\":5}]";
		
		String jsonStr = "{\"type\":\"1\",\"list\":\"100001005000000000|北京\",\"100001009000000000|上海\"  }";
		
		String jsonStr1 = "{\"type\":\"1\",\"list\":\"100001005000000000|北京100001009000000000|上海\"  }";
		
		String jsonStr2 = "{\"type\":\"1\",\"list\":{\"100001005000000000\":\"北京\",\"100001005000000001\":\"上海\"}}";
		
//		List lis= JsonUtils.toObjectList(pm,WbChanneltask.class);
//		
//		for(int i=0;i<lis.size();i++){
//			WbChanneltask w= (WbChanneltask)lis.get(i);
//		}
		
		System.out.println(toMap(jsonStr2));
	}
	
}
