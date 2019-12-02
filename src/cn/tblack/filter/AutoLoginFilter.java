package cn.tblack.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tblack.service.FactoryService;
import cn.tblack.utils.CookieMap;
import cn.tblack.utils.MD5Utils;

/**
 * <span>用于自动登录的过滤器</span>
 * @author TD唐登
 * @Date:2019年6月15日
 * @Version: 1.0(测试版)
 */
public class AutoLoginFilter extends HttpFilter{

	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/*@ 对请求进行拦截处理*/
		Cookie[] cookies =  request.getCookies();
		
		
		if(cookies != null)
		{
			CookieMap cMap =  new CookieMap(cookies);  //拿到Cookie对象
			
			String userName = cMap.get("username");
			String ssid = cMap.get("ssid");
			
			/*@ 之前存在Cookie数据， 并且该数据正确， 进入首页*/
			if(userName != null && 
					FactoryService.getUserService().count(userName) > 0 
					&& ssid.equals(MD5Utils.encrypt(userName))) {
				
				request.getSession().setAttribute("user",userName);
				response.sendRedirect(request.getContextPath() + "/homePage.html");
				return ;
			}
		}
		
		
		/*@ 正常的处理流程- 将请求交还给对象*/
		chain.doFilter(request, response);
	}
}
