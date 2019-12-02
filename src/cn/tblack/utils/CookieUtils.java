package cn.tblack.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <span>保存Cookie的工具类</span>
 * @author TD唐登
 * @Date:2019年6月13日
 * @Version: 1.0(测试版)
 */
public class CookieUtils {

	/**
	 *  @ 在指定的Request对象中添加Cookie， 该Cookie的失效时间为传递的天数
	 * @param key   键 
	 * @param value 值对
	 * @param req   存放Cookie的请求对象
	 * @param resp  Cookie的响应对象
	 * @param expired 失效的时间 (以秒为单位)
	 */
	public static void addCookie(String key, String value, HttpServletRequest req, HttpServletResponse resp,
			int expired) {

		Cookie cookie =  new Cookie(key,value);
		cookie.setMaxAge(expired);
		resp.addCookie(cookie);
		
	}
	
	public static void delCookie(Cookie cookie , HttpServletResponse resp) {
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
	}
	
}
