package test.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class JedisConnection {
	private Jedis jedis;

	public JedisConnection(Jedis jedis) {
		this.jedis = jedis;
	}

	public Jedis getJedis() {
		return jedis;
	}

	/**
	 * ���ֵ
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		jedis.set(key, value);
	}

	/**
	 * ��ȡֵ
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return jedis.get(key);
	}

	/**
	 * ɾ�������������������ͣ�
	 * 
	 * @param key
	 * @return
	 */
	public void del(String key) {
		jedis.del(key);
	}

	/**
	 * �ݼ� �����key�����ڷ���-1��������ڲ���Ϊ���ֵĻ��ͼ�һ,������Ǵ����ֵĻ�����׳����Ǵ����ֵ��쳣��
	 * 
	 * @param key
	 */
	public void decrease(String key) {
		jedis.decr(key);
	}

	/**
	 * ��num����
	 * 
	 * @param key
	 * @param num
	 */
	public void decrease(String key, int num) {
		jedis.decrBy(key, num);
	}

	/**
	 * ���� �����key�����ڷ���-1��������ڲ���Ϊ���ֵĻ��ͼ�һ,������Ǵ����ֵĻ�����׳����Ǵ����ֵ��쳣��
	 * 
	 * @param key
	 */
	public void increase(String key) {
		jedis.incr(key);
	}

	/**
	 * ��num����
	 * 
	 * @param key
	 * @param num
	 */
	public void increase(String key, int num) {
		jedis.incrBy(key, num);
	}

	/**
	 * ���map�ṹ
	 * 
	 * @param key
	 * @param map
	 */
	public void setMap(String key, Map<String, String> map) {
		if(map != null){
			jedis.hmset(key, map);
		}
	}

	/**
	 * ���list (�Ƚ��ȳ�ģʽ)
	 * 
	 * @param key
	 * @param list
	 */
	public void setList(String key, List<String> list) {
		if (list != null) {
			for (String s : list) {
				jedis.rpush(key, s);
			}
		}
	}

	/**
	 * ��ȡmap
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key) {

		HashMap<String, String> map = null;

		if (jedis.exists(key)) {

			map = new HashMap<String, String>();

			Iterator<String> iter = jedis.hkeys(key).iterator();
			while (iter.hasNext()) {
				String mk = iter.next();
				String mv = null;
				if (jedis.hmget(key, mk) != null) {
					mv = jedis.hmget(key, mk).get(0);
				}
				map.put(mk, mv);
			}
		}

		return map;
	}

	/**
	 * ��ȡlist
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getList(String key) {

		List<String> list = null;

		if (jedis.exists(key)) {
			list = jedis.lrange(key, 0, -1);
		}

		return list;
	}

}
