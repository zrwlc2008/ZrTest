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
		// �������������
		config.setMaxTotal(100000);
		// ���ÿ���������
		config.setMaxIdle(1000);
		// �����������ʱ�䣬���뵥λ
		config.setMaxWaitMillis(1000);
		try {
			/**
			 * ��������� java.net.SocketTimeoutException: Read timed out
			 * exception���쳣��Ϣ �볢���ڹ���JedisPool��ʱ�������Լ��ĳ�ʱֵ.
			 * JedisPoolĬ�ϵĳ�ʱʱ����2��(��λ����)
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
	 * ��ȡRedisʵ��.
	 * 
	 * @return Redis������ʵ��
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
				// ���ٶ���
				pool.returnResourceObject(jedis);
			}
			retry++;
		}
		// ��ȡjedis����3��
		while (jedis == null && retry < 3);

		if (jedis != null) {
			jedisConn = new JedisConn(jedis);
		}
		return jedisConn;
	}

	/**
	 * �ͷ�redisʵ�������ӳ�.
	 * 
	 * @param jedis
	 *            redisʵ��
	 */
	public static void closeConnection(JedisConn jedisConn) {
		if (jedisConn != null) {
			Jedis jedis = jedisConn.getJedis();
			pool.returnResourceObject(jedis);
		}
	}

}
