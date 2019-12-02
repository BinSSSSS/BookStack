package cn.tblack.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tblack.service.FactoryService;
import cn.tblack.utils.CookieMap;
import cn.tblack.utils.MD5Utils;

/**
 * <span>@用户访问的拦截器</span>
 * @author TD唐登
 * @Date:2019年6月24日
 * @Version: 1.0(测试版)
 */
/*@拦截所有需要用户登录访问的页面 */
@WebFilter(urlPatterns= {"/user/*"},
initParams= {
		@WebInitParam(name="filterType",value="html,htm,jsp")  //设置拦截类型
}) 
public class UserVisitFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		
		/*@ 拿到请求的文件路径*/
		String requestType =  request.getServletPath();
	
		/*@ 拿到请问的文件类型*/
		requestType = requestType.substring(requestType.lastIndexOf(".") + 1);  //拿到请求文件的类型
		
		/*@ 拿到初始配置的信息*/
		FilterConfig config = super.getFilterConfig();
		
		/*@ 拿到需要过滤类型的字符串并转换为Set*/
		Set<String> filterTypeSet =  new HashSet<>(Arrays.asList(config.getInitParameter("filterType").split(",")));
		
		/*@ 如果当前请求的文件不是需要过滤的类型，那么直接放行，让其访问*/
		if(!filterTypeSet.contains(requestType))
		{
			chain.doFilter(request, response);
			return;
		}
		
		/*@ 对请求进行验证， 如果不存在登录的状态的话， 那么则跳转到后台登录页面*/
		Cookie[] cookies = request.getCookies();
		
		
		/*@ 检查cookies对象， 是否存在用户登录的cookie*/
		if(cookies != null) {
			
			/*@ 拿到由cookie键值对组成的map， 进行cookie查询*/
			Map<String, String> cMap = new CookieMap(cookies); 
			
			String username =  cMap.get("username");
			String ssid =  cMap.get("ssid");
			
			
			/*@ 当存在Cookie对象， 并且账户和通行id对应的话，那么验证成功*/
			if(username != null && ssid != null 
					&& FactoryService.getUserService().count(username) > 0
					&& MD5Utils.encrypt(username).equals(ssid)) {
					
				//验证成功跳转到请求页面
				chain.doFilter(request, response);
				return;
			}
		}
		
		//未登录跳转到登录页面
		response.sendRedirect(request.getContextPath() + "/loginAndRegister.jsp");
	}
}
