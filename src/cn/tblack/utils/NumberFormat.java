package cn.tblack.utils;

import java.math.BigDecimal;

/**
 * <span>提供数字格式化的操作</span>
 * @author TD唐登
 * @Date:2019年6月22日
 * @Version: 1.0(测试版)
 */
public class NumberFormat {

	/**
	 * @ 通过一个long的长度来进行转换为以GB\MB\KB\bytes为单位换算的字符串
	 * @param pContentLength
	 * @return
	 */
	public static String getExactByteUnit(long pContentLength) {
		
		
		String size;
		
		/*@G字节*/
		if (pContentLength >= 1024 * 1024 * 1024) {
			size = String.format("%.2f GB", (double) pContentLength / (1024.0 * 1024.0 * 1024.0));
		}
		/*@兆字节*/
		else if (pContentLength >= 1024 * 1024) {
			size =  String.format("%.2f MB", (double) pContentLength / (1024.0 * 1024.0));
		}
		/*@K字节*/
		else  if (pContentLength >= 1024){
			size = String.format("%d KB",  pContentLength /  1024);
		}
		/*@字节*/
		else {
			size = String.format("%d Bytes", pContentLength);
		}
		return size;
	}
	
	
	/**
	 * @ 通过对象来转换为数值进行返回
	 * @param obj
	 * @return
	 */
	public static long getNum(Object obj) {
		if(obj instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) obj;
			return bd.longValue();
		}else if(obj instanceof Integer) {
			return (Integer)obj;
		}
		
		return 0;
	}
}
