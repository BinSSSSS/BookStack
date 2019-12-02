package cn.tblack.utils;

/**
 * <span>生成器接口，用于可以用于生成各种元素</span>
 * @author TD唐登
 * @Date:2019年6月16日
 * @Version: 1.0(测试版)
 * @param <T>
 */
public interface Generator<T> {

	
	public T next();
}
