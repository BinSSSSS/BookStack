package cn.tblack.utils;

/**
 * <span>同于的对象操作函数</span>
 * @author TD唐登
 * @Date:2019年6月21日
 * @Version: 1.0(测试版)
 */
public class Objects {

	
	/**
	 * @ 复制对象的引用到一个新数组当中
	 * @param sources
	 * @param newLenght
	 * @return
	 */
	public static Object[] referenceOf(Object[] sources, int newLength) {
		
		if(newLength >  sources.length)
			throw new RuntimeException("newLength can't max of sources length");
		
		if(newLength < 0 )
			throw new RuntimeException("newLength can't be negative");
		
		Object[] target = new Object[newLength];
		
		for(int i = 0; i < newLength; ++i) {
			target[i] = sources[i];
		}
		
		return target;
	}
}
