package cn.tblack.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tblack.service.FactoryService;
import cn.tblack.utils.CookieMap;
import cn.tblack.utils.MD5Utils;

/**
 * <span>访问后台过滤器- 当用户未登录或者权限不足的时候，无法登录到后台页面中</span>
 * @author TD唐登
 * @Date:2019年6月17日
 * @Version: 1.0(测试版)
 */
public class BackstageFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		/*@ 当请求登录页面的时候-可以直接响应到登录页面（不设置从登录页面登录到后台首页功能）
		 *@ bs_login.jsp页面放置在根目录下， 所有从 bs/请求登录页面也会跳转到根目录下的文件中*/
		if(request.getServletPath().endsWith("bs_login.jsp")) {
			response.sendRedirect(request.getContextPath() + "/bs_login.jsp");
			return;
		}
		
		/*@ 检验登录请求的页面是否存在Cookie对象， 并且Cookie对象是否是正确的Cookie
		 *@ 如果是正确的Cookie对象的话， 那么就可以访问指定的页面， 否则跳转到登录页面*/
		if(detectionRequest(request,response)){  
			chain.doFilter(request, response);
			return;
		}
		
		
		/*@当不满足条件的时候，则重定向到后台登录页面*/
		response.sendRedirect(request.getContextPath() + "/bs_login.jsp");
	}
	
	
	public static boolean detectionRequest(HttpServletRequest request, HttpServletResponse response) {
		

		/*@ 对请求进行验证， 如果不存在登录的状态的话， 那么则跳转到后台登录页面*/
		Cookie[] cookies = request.getCookies();
		
		
		/*@ 检查cookies对象， 是否存在用户登录的cookie*/
		if(cookies != null) {
			
			/*@ 拿到由cookie键值对组成的map， 进行cookie查询*/
			Map<String, String> cMap = new CookieMap(cookies); 
			
			String admin =  cMap.get("admin");
			String assid =  cMap.get("assid");
			
			
			/*@ 当存在Cookie对象， 并且管理员账户和通行id对应的话，那么验证成功*/
			if(admin != null && assid != null 
					&& FactoryService.getAdminService().count(admin) > 0
					&& MD5Utils.encrypt(admin).equals(assid)) {
					return true;
			}
		}
		
		return false;
	}

}
