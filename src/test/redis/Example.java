package test.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Example {

	private Jedis jedis;

	@Before
	public void setup() {
		// ����redis������
		jedis = JedisPoolUtil.getConnection().getJedis();
		//System.out.println(jedis);
		// Ȩ����֤
		// jedis.auth("admin");
	}

	/**
	 * redis����Map
	 */
	@Test
	public void testMap() {
		// -----�������----------
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "xinxin");
		map.put("age", "22");
		map.put("qq", "123456");
		jedis.hmset("user", map);
		// ȡ��user�е�name��ִ�н��:[minxr]-->ע������һ�����͵�List
		// ��һ�������Ǵ���redis��map�����key����������Ƿ���map�еĶ����key�������key���Ը�������ǿɱ����
		List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
		System.out.println(rsmap);
		
		//System.out.println(jedis.get("user"));

		// ɾ��map�е�ĳ����ֵ
		jedis.hdel("user", "age");
		System.out.println(jedis.hmget("user", "age")); // ��Ϊɾ���ˣ����Է��ص���null
		System.out.println(jedis.hlen("user")); // ����keyΪuser�ļ��д�ŵ�ֵ�ĸ���2
		System.out.println(jedis.exists("user"));// �Ƿ����keyΪuser�ļ�¼ ����true
		System.out.println(jedis.hkeys("user"));// ����map�����е�����key
		System.out.println(jedis.hvals("user"));// ����map�����е�����value
		System.out.println("--------------");
		Iterator<String> iter = jedis.hkeys("user").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("user", key));
		}
	}

	/**
	 * jedis����List (�Ƚ��ȳ�ģʽ)
	 */
	//@Test
	public void testList() {
		// ��ʼǰ�����Ƴ����е�����
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		// ����key java framework�д����������
		jedis.lpush("java framework", "spring");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		// ��ȡ����������jedis.lrange�ǰ���Χȡ����
		// ��һ����key���ڶ�������ʼλ�ã��������ǽ���λ�ã�jedis.llen��ȡ���� -1��ʾȡ������
		System.out.println(jedis.lrange("java framework", 0, -1));

		//System.out.println(jedis.get("java framework"));
		
		jedis.del("java framework");
		jedis.rpush("java framework", "spring");
		jedis.rpush("java framework", "struts");
		jedis.rpush("java framework", "hibernate");
		System.out.println(jedis.lrange("java framework", 0, -1));
	}

	/**
	 * jedis����Set
	 */
	//@Test
	public void testSet() {
		// ���
		jedis.sadd("user", "liuling");
		jedis.sadd("user", "xinxin");
		jedis.sadd("user", "ling");
		jedis.sadd("user", "zhangxinxin");
		jedis.sadd("user", "who");
		// �Ƴ�noname
		jedis.srem("user", "who");
		System.out.println(jedis.smembers("user"));// ��ȡ���м����value
		System.out.println(jedis.sismember("user", "who"));// �ж� who
															// �Ƿ���user���ϵ�Ԫ��
		System.out.println(jedis.srandmember("user"));
		System.out.println(jedis.scard("user"));// ���ؼ��ϵ�Ԫ�ظ���
	}

	//@Test
	public void test() throws InterruptedException {
		// jedis ����
		// ע�⣬�˴���rpush��lpush��List�Ĳ�������һ��˫���������ӱ��������ģ�
		jedis.del("a");// ��������ݣ��ټ������ݽ��в���
		jedis.rpush("a", "1");
		jedis.lpush("a", "6");
		jedis.lpush("a", "3");
		jedis.lpush("a", "9");
		System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
		System.out.println(jedis.sort("a")); // [1, 3, 6, 9] //�����������
		System.out.println(jedis.lrange("a", 0, -1));
	}
}
