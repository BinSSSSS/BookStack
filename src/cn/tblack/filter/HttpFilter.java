package cn.tblack.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <span>用于将Filter包装为更利用在Web项目中使用的HttpFilter</span>
 * @author TD唐登
 * @Date:2019年6月15日
 * @Version: 1.0(测试版)
 */
public abstract class HttpFilter implements Filter {

	private FilterConfig filterConfig;
	
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig =  filterConfig;
		init();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
		
	}

	@Override
	public void destroy() {
	}

	/**
	 * @ 该抽象函数是为了将doFilter函数的参数封装为HTTP类型的请求和响应对象进行处理
	 * @param req 
	 * @param resp
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException;
	
	
	/**
	 * @ 用于提供给派生类进行初始化使用
	 */
	protected void init() {
		// DEFUALT NONE
	}
}
