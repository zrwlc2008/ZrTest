package test.lang;

public class Test {

	public static void bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			System.out.println("1-- " + b[i]);
			int a = b[i] & 0xFF ;
			System.out.println("2--" + a );
			String hex = Integer.toHexString(b[i] & 0xFF);
			System.out.println("3--" + hex);
			if (hex.length() == 1) {
				hex = "0" + hex;
			}
			ret += hex.toUpperCase();
		}
		System.out.println(ret);
	}

	public static float getFloat(byte[] b) {
		int accum = 0;
		accum = accum | (b[0] & 0xff) << 0;
		accum = accum | (b[1] & 0xff) << 8;
		accum = accum | (b[2] & 0xff) << 16;
		accum = accum | (b[3] & 0xff) << 24;
		System.out.println(accum);
		return Float.intBitsToFloat(accum);
	}

	public static void main(String[] args) {
		Test.bytes2HexString("aaa".getBytes());
	}

}
