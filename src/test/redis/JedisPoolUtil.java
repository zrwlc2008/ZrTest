package test.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis������,���ڻ�ȡRedisPool. �ο�����˵�����£� You shouldn't use the same instance from
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
	 * ˽�й�����.
	 */
	private JedisPoolUtil() {

	}

	// private static Map<String, JedisPool> maps = new HashMap<String,
	// JedisPool>();

	private static JedisPool pool;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// �������������
		config.setMaxTotal(1000);
		// ���ÿ���������
		config.setMaxIdle(10);
		// �����������ʱ�䣬���뵥λ
		config.setMaxWaitMillis(1000);
		try {
			/**
			 * ��������� java.net.SocketTimeoutException: Read timed out
			 * exception���쳣��Ϣ �볢���ڹ���JedisPool��ʱ�������Լ��ĳ�ʱֵ.
			 * JedisPoolĬ�ϵĳ�ʱʱ����2��(��λ����)
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
	 * ��ȡRedisʵ��.
	 * 
	 * @return Redis������ʵ��
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
				// ���ٶ���
				pool.returnResourceObject(jedis);
			}
			retry++;
		}
		// ��ȡjedis����3��
		while (jedis == null && retry < 3);

		if (jedis != null) {
			jedisConn = new JedisConnection(jedis);
		}
		return jedisConn;
	}

	/**
	 * �ͷ�redisʵ�������ӳ�.
	 * 
	 * @param jedis
	 *            redisʵ��
	 */
	public static void closeConnection(JedisConnection jedisConn) {
		if (jedisConn != null) {
			Jedis jedis = jedisConn.getJedis();
			pool.returnResourceObject(jedis);
		}
	}

}
