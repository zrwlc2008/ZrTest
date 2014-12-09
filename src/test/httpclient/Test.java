package test.httpclient;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test {
	public static void main(String[] args) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {

			HttpGet httpGet = new HttpGet(
					"https://fxpa.fxcorporate.com/fxpa/getreport.app/?S=A10D25_09BFA27E7A096C7AE053DB293C0A50C2_12092014043620109882_E3Q&cn=MT4AUDDEMO&lc=enu&un=1418099737579&outFormat=xml&account=62017344&from=10/1/2014&till=11/1/2014&report_name=REPORT_NAME_CUSTOMER_ACCOUNT_STATEMENT");

			// 建立连接
			response = httpclient.execute(httpGet);

			// 解析返回参数
			HttpEntity entity = response.getEntity();

			String result = EntityUtils.toString(entity, getContentCharSet(entity));

			System.out.println(result);

			response.close();
			httpclient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 默认编码utf -8 Obtains character set of the entity, if known.
	 * 
	 * @param entity
	 *            must not be null
	 * @return the character set, or null if not found
	 * @throws ParseException
	 *             if header elements cannot be parsed
	 * @throws IllegalArgumentException
	 *             if entity is null
	 */
	private static String getContentCharSet(final HttpEntity entity) throws Exception {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}

		if (charset == null || charset.equals("")) {
			charset = "UTF-8";
		}
		return charset;
	}
}
