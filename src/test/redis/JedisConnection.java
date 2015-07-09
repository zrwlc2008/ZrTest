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
	 * 存放值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		jedis.set(key, value);
	}

	/**
	 * 获取值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return jedis.get(key);
	}

	/**
	 * 删除（用于所有数据类型）
	 * 
	 * @param key
	 * @return
	 */
	public void del(String key) {
		jedis.del(key);
	}

	/**
	 * 递减 （如果key不存在返回-1，如果存在并且为数字的话就减一,如果不是纯数字的话则会抛出不是纯数字的异常）
	 * 
	 * @param key
	 */
	public void decrease(String key) {
		jedis.decr(key);
	}

	/**
	 * 按num减少
	 * 
	 * @param key
	 * @param num
	 */
	public void decrease(String key, int num) {
		jedis.decrBy(key, num);
	}

	/**
	 * 递增 （如果key不存在返回-1，如果存在并且为数字的话就减一,如果不是纯数字的话则会抛出不是纯数字的异常）
	 * 
	 * @param key
	 */
	public void increase(String key) {
		jedis.incr(key);
	}

	/**
	 * 按num增加
	 * 
	 * @param key
	 * @param num
	 */
	public void increase(String key, int num) {
		jedis.incrBy(key, num);
	}

	/**
	 * 存放map结构
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
	 * 存放list (先进先出模式)
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
	 * 获取map
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
	 * 获取list
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
