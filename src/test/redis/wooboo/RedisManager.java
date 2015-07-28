package test.redis.wooboo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {

	protected static Logger log = Logger.getLogger(RedisManager.class);

	/**
	 * -----------初始化读取配置文件 ---------------------------------------
	 */
	private RedisManager() {

		String ip = null;
		int port = 0;
		String auth = null;
		int maxTotal = 0 ;
		int maxIdle = 0 ;
		int maxWaitMillis = 0 ;

		String ipRead = null;
		int portRead = 0;
		String authRead = null;
		int maxTotalRead = 0 ;
		int maxIdleRead = 0 ;
		int maxWaitMillisRead = 0 ;
		
		boolean isCfgOk = true;

		// 读取classes根目录的redis.properties
		Properties props = new Properties();
		try {
			ClassLoader cl = RedisManager.class.getClassLoader();
			InputStream is = cl.getResourceAsStream("redis.properties");
			props.load(is);

		} catch (Exception e) {
			log.error("读取redis配置文件失败，请检查配置文件是否放在classes根目录，并命名为redis.properties", e);
			props = null;
		}

		// for (Object s : props.keySet())
		// System.out.println(s);

		if (props != null) {
			//redis写服务器ip
			ip = props.getProperty("redis.write.ip");
			if (ip == null || ip.equals("")) {
				log.error("读取redis配置文件失败，请检查redis.write.ip是否为空");
				isCfgOk = false;
			}
			//redis写服务器端口
			try {
				port = Integer.parseInt(props.getProperty("redis.write.port"));
			} catch (Exception e) {
				log.error("读取redis配置文件失败，请检查redis.write.port是否为数字", e);
				isCfgOk = false;
			}
			// redis写服务器密码
			auth = props.getProperty("redis.write.auth");
			if (auth == null || auth.equals("")) {
				log.error("读取redis配置文件失败，请检查redis.write.auth是否为空");
				isCfgOk = false;
			}
			//redis写服务器最大连接数
			try {
				maxTotal = Integer.parseInt(props.getProperty("redis.write.maxTotal"));
			} catch (Exception e) {
				log.error("读取redis配置文件失败，请检查redis.write.maxTotal是否为数字", e);
				isCfgOk = false;
			}
			//最大闲置连接数
			try {
				maxIdle = Integer.parseInt(props.getProperty("redis.write.maxIdle"));
			} catch (Exception e) {
				log.error("读取redis配置文件失败，请检查redis.write.maxTotal是否为数字", e);
				isCfgOk = false;
			}
			//最大等待返回连接时间
			try {
				maxWaitMillis = Integer.parseInt(props.getProperty("redis.write.maxWaitMillis"));
			} catch (Exception e) {
				log.error("读取redis配置文件失败，请检查redis.write.maxTotal是否为数字", e);
				isCfgOk = false;
			}
			
			//--------------------------------------------------------------------------
			
			// redis读服务器ip
			ipRead = props.getProperty("redis.read.ip");
			if (ipRead == null || ipRead.equals("")) {
				log.error("读取redis配置文件失败，请检查redis.read.ip是否为空");
				isCfgOk = false;
			}
			// redis读服务器端口
			try {
				portRead = Integer.parseInt(props.getProperty("redis.read.port"));
			} catch (Exception e) {
				log.error("读取redis配置文件失败，请检查redis.read.port是否为数字", e);
				isCfgOk = false;
			}
			// redis读服务器密码
			authRead = props.getProperty("redis.read.auth");
			if (authRead == null || authRead.equals("")) {
				log.error("读取redis配置文件失败，请检查redis.read.auth是否为空");
				isCfgOk = false;
			}
			//redis写服务器最大连接数
			try {
				maxTotalRead = Integer.parseInt(props.getProperty("redis.read.maxTotal"));
			} catch (Exception e) {
				log.error("读取redis配置文件失败，请检查redis.read.maxTotal是否为数字", e);
				isCfgOk = false;
			}
			//最大闲置连接数
			try {
				maxIdleRead = Integer.parseInt(props.getProperty("redis.read.maxIdle"));
			} catch (Exception e) {
				log.error("读取redis配置文件失败，请检查redis.read.maxIdle是否为数字", e);
				isCfgOk = false;
			}
			//最大等待返回连接时间
			try {
				maxWaitMillisRead = Integer.parseInt(props.getProperty("redis.read.maxWaitMillis"));
			} catch (Exception e) {
				log.error("读取redis配置文件失败，请检查redis.read.maxWaitMillis是否为数字", e);
				isCfgOk = false;
			}

		} else {
			isCfgOk = false;
		}

		if (isCfgOk) {

			log.debug("读取redis配置文件成功");
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
				writePool = new JedisPool(config, ip, port, 2000, auth);
			} catch (Exception e) {
				log.error("创建redis写连接池失败，请检查ip和端口等设置.", e);
			}

			try {
				JedisPoolConfig config = new JedisPoolConfig();
				// 设置最大连接数
				config.setMaxTotal(maxTotalRead);
				// 设置空闲连接数
				config.setMaxIdle(maxIdleRead);
				// 设置最大阻塞时间，毫秒单位
				config.setMaxWaitMillis(maxWaitMillisRead);
				
				readPool = new JedisPool(config, ipRead, portRead, 2000, authRead);
			} catch (Exception e) {
				log.error("创建redis读连接池失败，请检查ip和端口等设置.", e);
			}

		}

	}

	/**
	 * ----------------初始化读取配置文件end---------------------------------------
	 */

	/**
	 * ----------------安全创建单例对象------------------------------
	 */
	private static class StaticHolder {
		static final RedisManager instance = new RedisManager();
	}

	public static RedisManager getInstance() {
		return StaticHolder.instance;
	}

	/**
	 * -----------------安全创建单例对象 end--------------------------------
	 */

	private ThreadLocal<OP> current = new ThreadLocal<OP>();

	private static JedisPool writePool;

	private static JedisPool readPool;

	private static int RETRY = 3;

	enum OP {
		write, read
	};

	/**
	 * 获取Redis实例.
	 * 
	 * @return Redis工具类实例
	 */
	private Jedis getJedis() throws Exception {
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
				// log.info("get redis master1!");
			} catch (Exception e) {
				log.error("第" + (count + 1) + "次获取redis连接失败，OP=" + current.get() + "，继续重试...", e);
				closeJedis(jedis);
			}
			count++;
		}
		// 获取jedis重试
		while (jedis == null && count < RETRY);

		if (jedis == null) {
			throw new RedisManagerException("由于连接池问题，最终无法获取redis连接，OP=" + current.get() + "，请检查连接池配置");
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
		if (key == null) {
			throw new Exception("key不能为空");
		}
		if (value == null) {
			throw new Exception("value不能为空");
		}
		// ！当前设为写模式
		current.set(OP.write);

		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.set(key, value);

		} catch (Exception e) {
			log.error("redis写数据失败，" + ArgumentsUtil.getArgumentsInfo(key, value), e);
			// throw new Exception("redis写数据失败");
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
		if (key == null) {
			throw new Exception("key不能为空");
		}
		// ！当前设为读模式
		current.set(OP.read);

		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			value = jedis.get(key);

		} catch (Exception e) {
			log.error("redis读数据失败，" + ArgumentsUtil.getArgumentsInfo(key), e);
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
		if (key == null) {
			throw new Exception("key不能为空");
		}
		// ！当前设为写模式
		current.set(OP.write);

		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);

		} catch (Exception e) {
			log.error("redis删除数据失败，" + ArgumentsUtil.getArgumentsInfo(key), e);
			// throw new Exception("redis删除数据失败");
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 递减 （如果key不存在返回-1，如果存在并且为数字的话就减一,如果不是纯数字的话则会抛出不是纯数字的异常）
	 * 
	 * @param key
	 */
	public void decrease(String key) throws Exception {
		if (key == null) {
			throw new Exception("key不能为空");
		}
		// ！当前设为写模式
		current.set(OP.write);

		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.decr(key);

		} catch (Exception e) {
			log.error("redis递减数据失败，" + ArgumentsUtil.getArgumentsInfo(key), e);
			// throw new Exception("redis递减数据失败");
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
		if (key == null) {
			throw new Exception("key不能为空");
		}
		// ！当前设为写模式
		current.set(OP.write);

		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.decrBy(key, num);

		} catch (Exception e) {
			log.error("redis递减数据失败，" + ArgumentsUtil.getArgumentsInfo(key), e);
			// throw new Exception("redis递减数据失败");
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 递增 （如果key不存在返回-1，如果存在并且为数字的话就减一,如果不是纯数字的话则会抛出不是纯数字的异常）
	 * 
	 * @param key
	 */
	public void increase(String key) throws Exception {
		if (key == null) {
			throw new Exception("key不能为空");
		}
		// ！当前设为写模式
		current.set(OP.write);

		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.incr(key);

		} catch (Exception e) {
			log.error("redis递增数据失败，" + ArgumentsUtil.getArgumentsInfo(key), e);
			// throw new Exception("redis递增数据失败");
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
		if (key == null) {
			throw new Exception("key不能为空");
		}
		// ！当前设为写模式
		current.set(OP.write);

		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.incrBy(key, num);

		} catch (Exception e) {
			log.error("redis递增数据失败，" + ArgumentsUtil.getArgumentsInfo(key), e);
			// throw new Exception("redis递增数据失败");
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
			throw new Exception("key不能为空");
		}
		if (map == null) {
			throw new Exception("map不能为空");
		}
		// ！当前设为写模式
		current.set(OP.write);

		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hmset(key, map);

		} catch (Exception e) {
			log.error("redis存放map失败，" + ArgumentsUtil.getArgumentsInfo(key, map), e);
			// throw new Exception("redis存放map失败");
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
		if (key == null) {
			throw new Exception("key不能为空");
		}
		if (list == null) {
			throw new Exception("list不能为空");
		}
		// ！当前设为写模式
		current.set(OP.write);

		Jedis jedis = null;
		try {
			jedis = getJedis();
			for (String s : list) {
				jedis.rpush(key, s);
			}

		} catch (Exception e) {
			log.error("redis存放list失败，" + ArgumentsUtil.getArgumentsInfo(key, list), e);
			// throw new Exception("redis存放list失败");
			throw e;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 获取map
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key) throws Exception {

		if (key == null) {
			throw new Exception("key不能为空");
		}
		// ！当前设为读模式
		current.set(OP.read);

		HashMap<String, String> map = null;

		Jedis jedis = null;
		try {
			jedis = getJedis();

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
			} else {
				throw new Exception("redis获取map，找不到相应的key:" + key);
			}
		} catch (Exception e) {
			log.error("redis获取map失败，" + ArgumentsUtil.getArgumentsInfo(key), e);
			// throw new Exception("redis获取map失败");
			throw e;
		} finally {
			closeJedis(jedis);
		}
		return map;
	}

	/**
	 * 获取list
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getList(String key) throws Exception {

		if (key == null) {
			throw new Exception("key不能为空");
		}
		// ！当前设为读模式
		current.set(OP.read);

		List<String> list = null;

		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key)) {
				list = jedis.lrange(key, 0, -1);
			} else {
				throw new Exception("redis获取list，找不到相应的key:" + key);
			}

		} catch (Exception e) {
			log.error("redis获取list失败，" + ArgumentsUtil.getArgumentsInfo(key), e);
			// throw new Exception("redis获取list失败");
			throw e;
		} finally {
			closeJedis(jedis);
		}

		return list;
	}
}
