package test.redis;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * ClassName:RedisUtil</br>
 * Function: 提供redis的操作工具类</br>
 *
 * @author   zhangrui</br>
 * @version  1.0</br>
 * @Date	 2015-7-22 下午2:31:53</br>
 * 
 */
public class RedisUtil_Majiahuan {

	private static Logger log = Logger.getLogger(RedisUtil_Majiahuan.class);
	
	private ThreadLocal<OP> current = new ThreadLocal<OP>();

	private static JedisPool writePool;

	private static JedisPool readPool;

	private static int RETRY = 3;

	enum OP {
		write, read
	};
	
	/**
	 * redis配置文件
	 */
	private static ResourceBundle redis = ResourceBundle.getBundle("redis");
	
	private RedisUtil_Majiahuan() {}
	
	static{
		
		// redis写服务器ip
		String ip = redis.getString("redis.write.ip");
		if(StringUtils.isBlank(ip)){
			log.error("redis.write.ip is null");
		}
		
		// redis写服务器端口
		String portStr = redis.getString("redis.write.port");
		if(StringUtils.isBlank(portStr)){
			log.error("redis.write.port is null");
		}
		int port = 0;
		try {
			port = Integer.parseInt(portStr);
		} catch (Exception e) {
			log.error("please check redis.write.port is number?");
		}
		
		// redis写服务器密码
		String auth = redis.getString("redis.write.auth");
		
		// redis写服务器最大连接数
		String maxTotalStr = redis.getString("redis.write.maxTotal");
		if(StringUtils.isBlank(maxTotalStr)){
			log.error("redis.write.maxTotal is null");
		}
		int maxTotal = 0 ;
		try {
			maxTotal = Integer.parseInt(maxTotalStr);
		} catch (Exception e) {
			log.error("please check redis.write.maxTotal is number?");
		}
		
		// 最大闲置连接数
		String maxIdleStr = redis.getString("redis.write.maxIdle");
		if(StringUtils.isBlank(maxIdleStr)){
			log.error("redis.write.maxIdle is null");
		}
		int maxIdle = 0;
		try {
			maxIdle = Integer.parseInt(maxIdleStr);
		} catch (Exception e) {
			log.error("please check redis.write.maxIdle is number?");
		}
		
		// 最大等待返回连接时间
		String maxWaitMillisStr = redis.getString("redis.write.maxWaitMillis");
		if(StringUtils.isBlank(maxWaitMillisStr)){
			log.error("redis.write.maxWaitMillis is null");
		}
		int maxWaitMillis = 0;
		try {
			maxWaitMillis = Integer.parseInt(maxWaitMillisStr);
		} catch (Exception e) {
			log.error("redis.write.maxWaitMillis is number?");
		}

		// redis读服务器ip
		String ipRead = redis.getString("redis.read.ip");
		if(StringUtils.isBlank(ipRead)){
			log.error("redis.read.ip is null");
		}
		
		// redis读服务器端口
		String portReadStr = redis.getString("redis.read.port");
		if(StringUtils.isBlank(portReadStr)){
			log.error("redis.read.port is null");
		}
		int portRead = 0;
		try {
			portRead = Integer.parseInt(portReadStr);
		} catch (Exception e) {
			log.error("please check redis.read.port is number?");
		}
		
		// redis读服务器密码
		String authRead = redis.getString("redis.read.auth");
		
		// redis写服务器最大连接数
		String maxTotalReadStr = redis.getString("redis.read.maxTotal");
		if(StringUtils.isBlank(maxTotalReadStr)){
			log.error("redis.read.maxTotal is null");
		}
		int maxTotalRead = 0;
		try {
			maxTotalRead = Integer.parseInt(maxTotalReadStr);
		} catch (Exception e) {
			log.error("please check redis.read.maxTotal is number?");
		}
		
		// 最大闲置连接数
		String maxIdleReadStr = redis.getString("redis.read.maxIdle");
		if(StringUtils.isBlank(maxIdleReadStr)){
			log.error("redis.read.maxIdle is null");
		}
		int maxIdleRead = 0;
		try {
			maxIdleRead = Integer.parseInt(maxIdleReadStr);
		} catch (Exception e) {
			log.error("please check redis.read.maxIdle is number?");
		}
		
		// 最大等待返回连接时间
		String maxWaitMillisReadStr = redis.getString("redis.read.maxWaitMillis");
		if(StringUtils.isBlank(maxWaitMillisReadStr)){
			log.error("redis.read.maxWaitMillis is null");
		}
		int maxWaitMillisRead = 0;
		try {
			maxWaitMillisRead = Integer.parseInt(maxWaitMillisReadStr);
		} catch (Exception e) {
			log.error("please check redis.read.maxWaitMillis is number?");
		}

		log.debug("redis.write.ip=" + ip);
		log.debug("redis.write.port=" + port);
		log.debug("redis.write.auth=" + auth);
		log.debug("redis.write.maxTotal=" + maxTotal);
		log.debug("redis.write.maxIdle=" + maxIdle);
		log.debug("redis.write.maxWaitMillis=" + maxWaitMillis);
		log.debug("redis.read.ip=" + ipRead);
		log.debug("redis.read.port=" + portRead);
		log.debug("redis.read.auth=" + authRead);
		log.debug("redis.read.maxTotal=" + maxTotalRead);
		log.debug("redis.read.maxIdle=" + maxIdleRead);
		log.debug("redis.read.maxWaitMillis=" + maxWaitMillisRead);

		try {
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置最大连接数
			config.setMaxTotal(maxTotal);
			// 设置空闲连接数
			config.setMaxIdle(maxIdle);
			// 设置最大阻塞时间，毫秒单位
			config.setMaxWaitMillis(maxWaitMillis);
			/**
			 * 如果你遇到 java.net.SocketTimeoutException: Read timed out
			 * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
			 * JedisPool默认的超时时间是2秒(单位毫秒)
			 */
			if(null == auth || auth.length() == 0){
				writePool = new JedisPool(config, ip, port, 2000);
			}else{
				writePool = new JedisPool(config, ip, port, 2000, auth);
			}
		} catch (Exception e) {
			log.error(" create redis wirte pool fail，please check ip or port", e);
		}

		try {
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置最大连接数
			config.setMaxTotal(maxTotalRead);
			// 设置空闲连接数
			config.setMaxIdle(maxIdleRead);
			// 设置最大阻塞时间，毫秒单位
			config.setMaxWaitMillis(maxWaitMillisRead);
			if(null == auth || auth.length() == 0){
				readPool = new JedisPool(config, ipRead, portRead, 2000);
			}else{
				readPool = new JedisPool(config, ipRead, portRead, 2000,authRead);
			}
		} catch (Exception e) {
			log.error("create redis read pool fail，please check ip or port.", e);
		}
		
	}
	

	private static class StaticHolder {
		static final RedisUtil_Majiahuan instance = new RedisUtil_Majiahuan();
	}

	public static RedisUtil_Majiahuan getInstance() {
		return StaticHolder.instance;
	}

	/**
	 * 获取Redis实例.
	 * 
	 * @return Redis工具类实例
	 */
	private Jedis getJedis() {
		Jedis jedis = null;
		int count = 0;
		do {
			try {
				if (current.get() == OP.write && writePool != null) {
					jedis = writePool.getResource();
				}
				if (current.get() == OP.read && readPool != null) {
					jedis = readPool.getResource();
				}
			} catch (Exception e) {
				log.error("" + (count + 1) + " times get redis connect fail，OP=" + current.get()+ "，trying...", e);
				closeJedis(jedis);
			}
			count++;
		}while (jedis == null && count < RETRY);

		if (jedis == null) {
			log.error("can not get redis connect，OP=" + current.get() + "，please check config");
			return null;
		} else {
			return jedis;
		}
	}

	/**
	 * 将jedis放回连接池
	 * 
	 * @param jedis
	 */
	private void closeJedis(Jedis jedis) {
		if (current.get() == OP.write && writePool != null) {
			writePool.returnResourceObject(jedis);
		}
		if (current.get() == OP.read && readPool != null) {
			readPool.returnResourceObject(jedis);
		}
	}

	/**
	 * 存放值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		if (value == null) {
			throw new Exception("valueis null");
		}
		current.set(OP.write);
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.set(key, value);
		} catch (Exception e) {
			log.error("redis写数据失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 获取值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		current.set(OP.read);
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			value = jedis.get(key);
		} catch (Exception e) {
			log.error("redis读数据失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
		return value;
	}

	/**
	 * 删除（用于所有数据类型）
	 * 
	 * @param key
	 * @return
	 */
	public void del(String key) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		// ！当前设为写模式
		current.set(OP.write);
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			log.error("redis删除数据失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}
	

	/**
	 * 递减 （如果key不存在返回-1，如果存在并且为数字的话就减一
	 * 如果不是纯数字的话则会抛出不是纯数字的异常）
	 * 
	 * @param key
	 */
	public void decrease(String key) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		current.set(OP.write);
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.decr(key);
		} catch (Exception e) {
			log.error("redis递减数据失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 按num减少
	 * 
	 * @param key
	 * @param num
	 */
	public void decrease(String key, int num) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		current.set(OP.write);
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.decrBy(key, num);
		} catch (Exception e) {
			log.error("redis递减数据失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 递增 （如果key不存在返回-1，如果存在并且为数字的话就减一
	 * 如果不是纯数字的话则会抛出不是纯数字的异常）
	 * 
	 * @param key
	 */
	public void increase(String key) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		current.set(OP.write);
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.incr(key);
		} catch (Exception e) {
			log.error("redis递增数据失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 按num增加
	 * 
	 * @param key
	 * @param num
	 */
	public void increase(String key, int num) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		current.set(OP.write);
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.incrBy(key, num);
		} catch (Exception e) {
			log.error("redis递增数据失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 存放map结构
	 * 
	 * @param key
	 * @param map
	 */
	public void setMap(String key, Map<String, String> map) throws Exception {
		if (key == null) {
			throw new Exception("key is null");
		}
		if (map == null) {
			throw new Exception("map is null");
		}
		current.set(OP.write);
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hmset(key, map);

		} catch (Exception e) {
			log.error("redis存放map失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 存放list结构(先进先出模式)
	 * 
	 * @param key
	 * @param list
	 */
	public void setList(String key, List<String> list) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		if (null == list || list.size() == 0) {
			throw new Exception("list is null");
		}
		current.set(OP.write);
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.rpush(key, (String[])list.toArray());
		} catch (Exception e) {
			log.error("redis存放list失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}


	/**
	 * 获取list
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getList(String key) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		current.set(OP.read);
		List<String> list = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key)) {
				list = jedis.lrange(key, 0, -1);
			}
		} catch (Exception e) {
			log.error("redis获取list失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
		return list;
	}
	
	/**
	 * 模糊查询keys列表
	 * 
	 * @param pattern 
	 * @return
	 */
	public Set<String> getKeys(String pattern) throws Exception {
		if (null == pattern || pattern.length() == 0) {
			throw new Exception("key is null");
		}
		current.set(OP.read);
		Set<String> keys = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			keys = jedis.keys(pattern);
		} catch (Exception e) {
			log.error("redis读数据失败，", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
		return keys;
	}
	
	/**
	 * 模糊查询keys列表
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key) throws Exception {
		if (null == key || key.length() == 0) {
			throw new Exception("key is null");
		}
		current.set(OP.read);
		Map<String, String> map = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			map = jedis.hgetAll(key);
		} catch (Exception e) {
			log.error("redis读数据失败", e);
			throw e;
		} finally {
			closeJedis(jedis);
		}
		return map;
	}
	
	
}
