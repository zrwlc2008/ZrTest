package test.redis.wooboo;

public class RedisManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedisManagerException() {

	}

	public RedisManagerException(String msg) {
		super(msg);
	}

	public RedisManagerException(String msg, Throwable e) {
		super(msg, e);
	}
}
