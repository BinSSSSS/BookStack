package cn.tblack.utils;

import java.util.HashMap;

import javax.servlet.http.Cookie;

/**
 * <span>将Cookie的名字和值包装为一个类</span>
 * @author TD唐登
 * @Date:2019年6月15日
 * @Version: 1.0(测试版)
 */
public class CookieMap extends HashMap<String, String>{


	private static final long serialVersionUID = 1L;

	
	public CookieMap() {
		
	}
	
	/**
	 * @ 将Cookie数组中的数据包装为一个Map对象
	 * @param cookies
	 */
	public CookieMap(Cookie[] cookies) {
	
		if(cookies ==  null) {
			throw new RuntimeException("当前用户未登录");
		}
		
		for(Cookie cookie : cookies) {
			put(cookie.getName(), cookie.getValue());
		}
	}
}
