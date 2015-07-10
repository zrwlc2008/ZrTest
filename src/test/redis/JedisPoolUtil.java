package test.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	protected static Logger log = LoggerFactory.getLogger(JedisPoolUtil.class);

	private JedisPoolUtil() {
	}

	private static JedisPool writePool;
	
	private static JedisPool readPool;
	
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(100000);
		// 设置空闲连接数
		config.setMaxIdle(1000);
		// 设置最大阻塞时间，毫秒单位
		config.setMaxWaitMillis(1000);
		try {
			/**
			 * 如果你遇到 java.net.SocketTimeoutException: Read timed out
			 * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
			 * JedisPool默认的超时时间是2秒(单位毫秒)
			 */
			String ip = "192.168.1.200";
			int port = 6379;
			int timeout = 2000;
			writePool = new JedisPool(config, ip, port, timeout);
			
			readPool = new JedisPool(config, ip, port, timeout);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Redis实例.
	 * 
	 * @return Redis工具类实例
	 */
	public static JedisConn getConnection() {
		JedisConn jedisConn = null;
		Jedis jedis = null;
		int retry = 0;
		do {
			try {
				jedis = pool.getResource();
				jedis.auth("123456");
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
			jedisConn = new JedisConn(jedis);
		}
		return jedisConn;
	}

	/**
	 * 释放redis实例到连接池.
	 * 
	 * @param jedis
	 *            redis实例
	 */
	public static void closeConnection(JedisConn jedisConn) {
		if (jedisConn != null) {
			Jedis jedis = jedisConn.getJedis();
			pool.returnResourceObject(jedis);
		}
	}

}
