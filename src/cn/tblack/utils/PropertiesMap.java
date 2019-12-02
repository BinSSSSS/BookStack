package cn.tblack.utils;

import java.util.HashMap;
import java.util.Properties;

/**
 * <span>该类保存着properties文件的配置信息，以map的方式进行保存</span>
 * <span>该类为抽象类， 无法被实例化， 之后的类可以继承该类来完成功能。</span>
 * @author TD唐登
 * @Date:2019年6月21日
 * @Version: 1.0(测试版)
 */
public abstract class PropertiesMap extends HashMap<String,String>{

	private static final long serialVersionUID = 1L;

	public PropertiesMap(Properties properties) {
		init(properties);
	}
	
	/*@ 初始化操作， 通过传递一个properties对象来进行构造map对象 */
	public void init(Properties properties) {
		
		this.clear(); //无论是否是第一次构造，清除之前容器内的内容。
		
		for(Object key : properties.keySet()) {
			this.put((String) key,properties.getProperty((String) key));
		}
	}	
	
	
}
