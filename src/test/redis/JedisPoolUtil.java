package test.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工具类,用于获取RedisPool. 参考官网说明如下： You shouldn't use the same instance from
 * different threads because you'll have strange errors. And sometimes creating
 * lots of Jedis instances is not good enough because it means lots of sockets
 * and connections, which leads to strange errors as well. A single Jedis
 * instance is not threadsafe! To avoid these problems, you should use
 * JedisPool, which is a threadsafe pool of network connections. This way you
 * can overcome those strange errors and achieve great performance. To use it,
 * init a pool: JedisPool pool = new JedisPool(new JedisPoolConfig(),
 * "localhost"); You can store the pool somewhere statically, it is thread-safe.
 * JedisPoolConfig includes a number of helpful Redis-specific connection
 * pooling defaults. For example, Jedis with JedisPoolConfig will close a
 * connection after 300 seconds if it has not been returned.
 * 
 * @author wujintao
 */
public class JedisPoolUtil {
	protected static Logger log = LoggerFactory.getLogger(JedisPoolUtil.class);

	/**
	 * 私有构造器.
	 */
	private JedisPoolUtil() {

	}

	// private static Map<String, JedisPool> maps = new HashMap<String,
	// JedisPool>();

	private static JedisPool pool;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(1000);
		// 设置空闲连接数
		config.setMaxIdle(10);
		// 设置最大阻塞时间，毫秒单位
		config.setMaxWaitMillis(1000);
		try {
			/**
			 * 如果你遇到 java.net.SocketTimeoutException: Read timed out
			 * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
			 * JedisPool默认的超时时间是2秒(单位毫秒)
			 */
			String ip = "192.168.1.200";
			int port = 6300;
			int timeout = 2000;
			pool = new JedisPool(config, ip, port, timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Redis实例.
	 * 
	 * @return Redis工具类实例
	 */
	public static JedisConnection getConnection() {
		JedisConnection jedisConn = null;
		Jedis jedis = null;
		int retry = 0;
		do {
			try {
				jedis = pool.getResource();
				// log.info("get redis master1!");
			} catch (Exception e) {
				// log.error("get redis master1 failed!", e);
				e.printStackTrace();
				// 销毁对象
				pool.returnResourceObject(jedis);
			}
			retry++;
		}
		// 获取jedis重试3次
		while (jedis == null && retry < 3);

		if (jedis != null) {
			jedisConn = new JedisConnection(jedis);
		}
		return jedisConn;
	}

	/**
	 * 释放redis实例到连接池.
	 * 
	 * @param jedis
	 *            redis实例
	 */
	public static void closeConnection(JedisConnection jedisConn) {
		if (jedisConn != null) {
			Jedis jedis = jedisConn.getJedis();
			pool.returnResourceObject(jedis);
		}
	}

}
