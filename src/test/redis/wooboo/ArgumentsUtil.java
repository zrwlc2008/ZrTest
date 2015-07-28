package test.redis.wooboo;

import org.apache.log4j.Logger;

/**
 * 参数信息打印工具
 * 
 * @author zhang.rui
 * 
 */
public class ArgumentsUtil {
	/**
	 * 记录logger日志
	 */
	private static final Logger logger = Logger.getLogger(JsonUtils.class);

	/**
	 * 得到参数打印信息
	 * 
	 * @param args
	 */
	public static String getArgumentsInfo(Object... args) {

		try {
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < args.length; i++) {

				sb.append("参数[" + i + "]=");

				if (args[i] == null || args[i] instanceof String
						|| args[i] instanceof Integer
						|| args[i] instanceof Float
						|| args[i] instanceof Double) {
					sb.append(args[i] + ",");
				} else {
					sb.append(JsonUtils.ObjToJson(args[i]) + ",");
				}
			}

			return sb.toString();
		} catch (Exception e) {
			logger.error("参数信息打印工具getArgumentsInfo发生错误，将返回null", e);
			return null ;
		}
	}

	public static void main(String[] args) {
		System.out.println(ArgumentsUtil.getArgumentsInfo("1111", null, "222"));
	}

}
