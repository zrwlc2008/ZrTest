import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MyTest {
	
	public static double getD(){
		return 200000000000000.15 ;
	}
	
	/**
	 * 为了解决一个19位的long型数字在显示中变成科学记数法的问题
	 */
	public static void testShowNumber(){
		
		//没有变成科学记数法
		double i = getD() ;
		//显示时变成科学记数法
		System.out.println(i);
		//没有变成科学记数法
		double w = Double.parseDouble(i+"");
		//显示时变成科学记数法
		System.out.println(w);
		
		DecimalFormat f=new DecimalFormat(",###.##");
		//没有变成科学记数法并格式化
		System.out.println(f.format(i));
		//没有变成科学记数法并格式化
		System.out.println(f.format(w));
	}
	
	/**
	 * substring测试
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
	 * 学习数字格式化对象
	 */
	public static void testDecimalFormat() {
		// ---------------------------------------------
		//".##"，即如果有小数则保留2位
		//".00"，即强制保留2位小数，缺少的用0补齐
		DecimalFormat a = new DecimalFormat("##0.00");
		
		String s = a.format(0);
		System.err.println(s);
		
		s = a.format(03.3);
		System.err.println(s);
		
		s = a.format(03.335);
		System.err.println(s);
		
		s = a.format(03.3);
		System.err.println(s);
		// 说明：如果小数点后面不够2位小数，不会补零.参见Rounding小节
		// ---------------------------------------------

		// -----------------------------------------------
		// 可以在运行时刻用函数applyPattern(String)修改格式模板
		// 保留2位小数，如果小数点后面不够2位小数会补零
		a.applyPattern(".00");
		s = a.format(333.3);
		System.err.println(s);
		// ------------------------------------------------

		// ------------------------------------------------
		// 添加千分号
		a.applyPattern(".##\u2030");
		s = a.format(0.78934);
		System.err.println(s);// 添加千位符后,小数会进三位并加上千位符
		// ------------------------------------------------

		// ------------------------------------------------
		// 添加百分号
		a.applyPattern(".##%");
		s = a.format(0.78645);
		System.err.println(s);
		// ------------------------------------------------

		// ------------------------------------------------
		// 添加前、后修饰字符串，记得要用单引号括起来
		a.applyPattern("'这是我的钱$',###.###'美圆'");
		s = a.format(2533333443.3333);
		System.err.println(s);
		// ------------------------------------------------

		// ------------------------------------------------
		// 添加货币表示符号(不同的国家，添加的符号不一样
		a.applyPattern("\u00A4");
		s = a.format(34);
		System.err.println(s);
		// ------------------------------------------------

		// -----------------------------------------------
		// 定义正负数模板,记得要用分号隔开
		a.applyPattern("0.0;'@'-#.0");
		s = a.format(33);
		System.err.println(s);
		
		s = a.format(-33);
		System.err.println(s);
		// -----------------------------------------------

		// 综合运用，正负数的不同前后缀
		String pattern = "'my moneny ',###.##' RMB';'my debt ',###.##' US'";
		a.applyPattern(pattern);
		System.err.println(a.format(-1223233.456));
	}
	
	/**
	 * if,elseif练习
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
	 * 学习class.getResource方法，最后取到了 bin/com/test/test.txt
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
		//一定要从大到小
		VIP(20000,"stp_ZF_7","贵宾账户"),
		STAND(3000,"stp_ZF_12","标准账户"),
		MINI(50,"stp_ZF_18","迷你账户");
		
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
		
		//根据code获取名称
        public static String getNameByCode(String code) {
            for (LiveGroups g : LiveGroups.values()) {
                if (g.getCode().equals(code)) {
                    return g.getName() ;
                }
            }
            return "unknown" ;
        }
		
		//根据金额获取code
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
