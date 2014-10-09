import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MyTest {
	
	public static double getD(){
		return 200000000000000.15 ;
	}
	
	/**
	 * Ϊ�˽��һ��19λ��long����������ʾ�б�ɿ�ѧ������������
	 */
	public static void testShowNumber(){
		
		//û�б�ɿ�ѧ������
		double i = getD() ;
		//��ʾʱ��ɿ�ѧ������
		System.out.println(i);
		//û�б�ɿ�ѧ������
		double w = Double.parseDouble(i+"");
		//��ʾʱ��ɿ�ѧ������
		System.out.println(w);
		
		DecimalFormat f=new DecimalFormat(",###.##");
		//û�б�ɿ�ѧ����������ʽ��
		System.out.println(f.format(i));
		//û�б�ɿ�ѧ����������ʽ��
		System.out.println(f.format(w));
	}
	
	/**
	 * substring����
	 */
	public static void testSubString(){
		
		String s = "as.df.asdfa.txt" ;
		System.out.println(s.substring(s.lastIndexOf(".")+1));
		
		s = "http://i-lc.svnmishu.com/js/set.js?v=1.3" ;
		System.out.println(s.substring(0 , s.lastIndexOf("/js/")));
		
		s = "ZF_1_ZF_OutputRecordBean_1405390669736" ;
		s = s.substring(3,s.length()-1);
		System.out.println(s);
		s = s.substring(0,s.indexOf("_"));
		System.out.println(s);
	}
	
	/**
	 * ѧϰ���ָ�ʽ������
	 */
	public static void testDecimalFormat() {
		// ---------------------------------------------
		//".##"���������С������2λ
		//".00"����ǿ�Ʊ���2λС����ȱ�ٵ���0����
		DecimalFormat a = new DecimalFormat("##0.00");
		
		String s = a.format(0);
		System.err.println(s);
		
		s = a.format(03.3);
		System.err.println(s);
		
		s = a.format(03.335);
		System.err.println(s);
		
		s = a.format(03.3);
		System.err.println(s);
		// ˵�������С������治��2λС�������Ჹ��.�μ�RoundingС��
		// ---------------------------------------------

		// -----------------------------------------------
		// ����������ʱ���ú���applyPattern(String)�޸ĸ�ʽģ��
		// ����2λС�������С������治��2λС���Ჹ��
		a.applyPattern(".00");
		s = a.format(333.3);
		System.err.println(s);
		// ------------------------------------------------

		// ------------------------------------------------
		// ���ǧ�ֺ�
		a.applyPattern(".##\u2030");
		s = a.format(0.78934);
		System.err.println(s);// ���ǧλ����,С�������λ������ǧλ��
		// ------------------------------------------------

		// ------------------------------------------------
		// ��Ӱٷֺ�
		a.applyPattern(".##%");
		s = a.format(0.78645);
		System.err.println(s);
		// ------------------------------------------------

		// ------------------------------------------------
		// ���ǰ���������ַ������ǵ�Ҫ�õ�����������
		a.applyPattern("'�����ҵ�Ǯ$',###.###'��Բ'");
		s = a.format(2533333443.3333);
		System.err.println(s);
		// ------------------------------------------------

		// ------------------------------------------------
		// ��ӻ��ұ�ʾ����(��ͬ�Ĺ��ң���ӵķ��Ų�һ��
		a.applyPattern("\u00A4");
		s = a.format(34);
		System.err.println(s);
		// ------------------------------------------------

		// -----------------------------------------------
		// ����������ģ��,�ǵ�Ҫ�÷ֺŸ���
		a.applyPattern("0.0;'@'-#.0");
		s = a.format(33);
		System.err.println(s);
		
		s = a.format(-33);
		System.err.println(s);
		// -----------------------------------------------

		// �ۺ����ã��������Ĳ�ͬǰ��׺
		String pattern = "'my moneny ',###.##' RMB';'my debt ',###.##' US'";
		a.applyPattern(pattern);
		System.err.println(a.format(-1223233.456));
	}
	
	/**
	 * if,elseif��ϰ
	 */
	public static void testIf(){
		int i = 6 ;
		if(i >= 7){
			System.out.println("7");
		}
		else if(i >= 6){
			System.out.println("6");
		}
		else if(i >= 5){
			System.out.println("5");
		}else{
			System.out.println("?");
		}
	}
	
	/**
	 * ѧϰclass.getResource���������ȡ���� bin/com/test/test.txt
	 */
	public static void testClassPath(){
		String classPathRoot = MyTest.class.getResource("/com/test/").getFile();
		System.out.println(classPathRoot);
		
		File file = new File(classPathRoot + "test.txt");
		System.out.println(file.exists());
	}
	
	public static void testMinus(){
		int m = 3 ;
		for (; m >= 0 ;) {
			System.out.println(m--);
		}
	}
	
	
	public static enum LiveGroups {
		//һ��Ҫ�Ӵ�С
		VIP(20000,"stp_ZF_7","����˻�"),
		STAND(3000,"stp_ZF_12","��׼�˻�"),
		MINI(50,"stp_ZF_18","�����˻�");
		
		private int amount;
		private String code;
		private String name;
		
		private LiveGroups(int amount, String code, String name) {
			this.amount = amount;
	        this.code = code;
	        this.name = name;
	    }

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		//����code��ȡ����
        public static String getNameByCode(String code) {
            for (LiveGroups g : LiveGroups.values()) {
                if (g.getCode().equals(code)) {
                    return g.getName() ;
                }
            }
            return "unknown" ;
        }
		
		//���ݽ���ȡcode
        public static String[] getInfoByAmount(double amount) {
            for (LiveGroups g : LiveGroups.values()) {
                if (amount >= g.getAmount()) {
                    return new String[]{g.getCode(),g.getName()} ;
                }
            }
            return new String[]{LiveGroups.MINI.getCode(),LiveGroups.MINI.getName()} ;
        }
				
	}
	
	public static void testEnum(){
		
//		for (LiveGroups g : LiveGroups.values()) {
//           System.out.println(g.getName());
//        }
		
		System.out.println(LiveGroups.getInfoByAmount(20100)[1]);
		
	}
	
	
	public static void main(String[] args) {
		
		//testSubString();
		
		//DecimalFormat a = new DecimalFormat("#.00");
		//System.out.println(a.format(11111230.422));
		
		BigDecimal bd=new BigDecimal(450 - 0.17);
		System.out.println(bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		
		System.out.println(Math.round((28662d / 3600)));
		
	}
}
