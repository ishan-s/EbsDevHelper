package fnd.ishrivas.edh.common;

public class SystemUtils {
	
	static String ORIGINAL_LINE_SEP = System.getProperty("line.separator");
	
	public static void setUnixLineSeparator(){
		System.setProperty("line.separator", "\n");
	}
	
	public static void resetLineSeperator(){
		System.setProperty("line.separator", ORIGINAL_LINE_SEP);
	}

}
