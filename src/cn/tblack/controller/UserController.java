package cn.tblack.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.tblack.model.BookUser;
import cn.tblack.service.FactoryService;
import cn.tblack.service.UploadFileService;
import cn.tblack.service.UserService;
import cn.tblack.utils.AccountGenerator;
import cn.tblack.utils.Captcha;
import cn.tblack.utils.CookieMap;
import cn.tblack.utils.CookieUtils;
import cn.tblack.utils.MD5Utils;
import cn.tblack.utils.UploadUtils;

/**
 * <span>用户控制层， 用于和服务器进行交互</span>
 * 
 * @author TD唐登
 * @Date:2019年6月15日
 * @Version: 1.0(测试版)
 */

@WebServlet(urlPatterns = { "*.do" })
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService uService = FactoryService.getUserService();
	private UploadFileService ufService = FactoryService.getUploadFileService();

	private static Random  rand = new Random(); 

	public static final int DEFAULT_COOKIE_AGE = 24 * 60 * 60; // 默认Cookie的失效时间

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* @ 对请求数据进行统一编码 */
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		/* @ 通过反射技术来调用请求方法 */
		String post = req.getServletPath();

		/* @ 获取请求方法 */
		post = post.substring(post.lastIndexOf("/") + 1, post.lastIndexOf("."));
		
//		System.out.println(post);
		
		/* <span>使用反射技术来找到post请求的方法. 并调用该方法</span> */
		try {

			Method method = getClass().getDeclaredMethod(post, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @ 登录方法
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String account = req.getParameter("account");
		String password = req.getParameter("upassword");

		/* @ 直接请求的时候不带参数，那么直接转到登录页面 */
		if (account == null || password == null) {
			forwardToLR(req, resp);
			return;
		}

		/* @ 首先通过账号来查找 */
		BookUser u = uService.queryByAccount(Long.parseLong(account));

		/* @ 如果查找到了该账号的用户， 表示当前正在使用账号登录,如果为空表示正在使用手机号登录 */
		if (u == null) {
			u = uService.queryByPhone(Long.parseLong(account));
		}

		/* @ 当该用户存在， 并且该用户的密码与传入的一致的话，那么则设置Cookie */
		if (u != null && u.getPassword().equals(password)) {
			Cookie unCookie = new Cookie("username", u.getUserName()); // 用户名Cookie
			Cookie ssidCookie = new Cookie("ssid", MD5Utils.encrypt(u.getUserName())); // 用户通行id

			/* @ 设置Cookie的有效访问路径 */
			unCookie.setPath(req.getContextPath());
			ssidCookie.setPath(req.getContextPath());

			/* @ 设置为默认失效的时间 */
			unCookie.setMaxAge(DEFAULT_COOKIE_AGE);
			ssidCookie.setMaxAge(DEFAULT_COOKIE_AGE);

			/* @ 添加Cookie对象 */
			resp.addCookie(unCookie);
			resp.addCookie(ssidCookie);

			/* @ 删除request中的tips属性 */
//			req.removeAttribute("tips");
//			req.removeAttribute("account");

			/* @ 设置会话信息到请求对象 */
			/* @ 重定向到首页 */
			req.getSession().setAttribute("username", unCookie.getValue());

			resp.sendRedirect(req.getContextPath() + "/homePage.jsp");
		} else {
			/* @ 用户名或密码错误，重新转到登录页面 */
			req.setAttribute("tips", "用户名或密码错误");
			req.setAttribute("account", account);
//			resp.sendRedirect(req.getContextPath() + "/loginAndRegister.jsp");
			forwardToLR(req, resp);
		}
	}

	/**
	 * @ 退出登录状态
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* @ 清除会话内的属性值 */
		req.getSession().removeAttribute("username");

		/* @ 清除Cookie对象 */

		Cookie[] cookies = req.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				CookieUtils.delCookie(cookie, resp);
			} else if (cookie.getName().equals("ssid")) {
				CookieUtils.delCookie(cookie, resp);
			}
		}

		/* @ 清除完成之后重定向到当前页面 */
		req.getRequestDispatcher("/homePage.jsp").forward(req, resp);
//		forwardToLR(req, resp);
	}

	/**
	 * @ 注册用户
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* @传递过来的参数全部为空的情况下直接进行返回 */
		if (req.getParameterNames() == null) {
			forwardToLR(req, resp);
			return;
		}

		/* @ 将传送过来的数据进行检查- 是否之前存在该手机号或者是之前是否存在了该用户名 */
		BookUser u = new BookUser();
		boolean hasDupli = false; // 检查是否存在之前已经注册的数据
		String orgCode = (String) req.getSession().getAttribute("checkCode"); // 原始验证码
		String[] tarCode = null; // 输入的验证码

		try {
			Map<String, String[]> map = new LinkedHashMap<>(req.getParameterMap());

			tarCode = map.remove("checkCode");

			for (Entry<String, String[]> entry : map.entrySet()) {
				System.out.println("name:" + entry.getKey() + "value: " + entry.getValue()[0]);
			}

			BeanUtils.populate(u, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* @ 验证码不同， 直接显示错误信息并返回 */
		if (!orgCode.equalsIgnoreCase(tarCode[0])) {
			req.setAttribute("code_tips", "验证码错误!");
			System.out.println("验证码错误");
			hasDupli = true;
		} else {
			if (u.getUserName() == null || uService.count(u.getUserName()) > 0) {
				req.setAttribute("name_tips", "该用户名已存在!");
				System.out.println("该用户名已存在!");
				hasDupli = true;
			}
			if (u.getPhoneNum() == null || uService.countByPhone(Long.parseLong(u.getPhoneNum())) > 0) {
				req.setAttribute("phone_tips", "该手机号已注册!");
				System.out.println("该手机已存在!");
				hasDupli = true;
			}
		}

//		System.out.println("初始化完成: " + u);

		/* @ 如果没有使用已经注册过的手机号或者是用户名的话,那么则注册成功 */
		if (!hasDupli) {

			if (u.getPhoneNum().isEmpty() || u.getUserName().isEmpty() || u.getPassword().length() < 8) {
				/* @ 当用户修改了前端代码或者提交了错误的数据的时候， 直接响应404 */
				resp.sendError(404);
				return;
			}

			/* @ 生成一个新的账号 */
			u.setAccount(Long.toString(new AccountGenerator().next()));

			/* @ 插入到数据库之中 */
			System.out.println(uService.insert(u));

			/* @设置Session信息 */
			req.setAttribute("uAccount", u.getAccount());

			/* @ 清除之前设置的tips属性 */
			req.removeAttribute("name_tips");
			req.removeAttribute("phone_tips");

			/* @转发到登录和注册页面 */
			forwardToLR(req, resp);

			/* @ 重定向到登录页面 */
//			resp.sendRedirect(req.getContextPath() + "/loginAndRegister.jsp");
			return;

		}

		/* @设置跳转到注册页面 */
		req.setAttribute("link", "toregister");
		req.setAttribute("username", u.getUserName());
		req.setAttribute("phoneNum", u.getPhoneNum());
		/* @ 转发到登录和注册的页面 */
		forwardToLR(req, resp);
	}

	/**
	 * @ 生成验证码图片
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Pargma","no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpg");
		
		
		System.out.println(resp);
		
		Captcha captcha = Captcha.getInstance();

		/* @ 每次请求设置随机的验证码 */
		captcha.setCode(captcha.randomCode());

		/* @ 在会话中设置该验证码 */
		req.getSession().setAttribute("checkCode", captcha.getCode());

		ImageIO.write(captcha.drawCheckImg(), "jpg", resp.getOutputStream());

	}

	

	
	

	/**
	 * @ 用户上传文件时触发
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void uploadfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			CookieMap cMap = new CookieMap(req.getCookies());
			String userName = cMap.get("username");
			
			upload(req,resp,userName);
		}catch(Exception e) {
			resp.getWriter().println(e.getMessage());
		}
	}

	/**
	 * @ 转发到普通用户的登录和注册页面
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void forwardToLR(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/loginAndRegister.jsp").forward(req, resp);
	}

	

	/**
	 * @ 实际的上传操作， 通过传递用户名来进行查找用户，并传递给上传文件UploadFileService类
	 * @param req
	 * @param resp
	 * @param userName
	 * @return
	 * @throws IOException
	 */
	private boolean upload(HttpServletRequest req, HttpServletResponse resp, String userName) throws IOException {
		
		if(userName == null)
		{
			resp.getOutputStream().write("请登录之后再进行文件的上传！".getBytes());
			return false;
		}
		
		/*@ 使用更为通用的操作来完成文件的上传*/
		return UploadUtils.upload(req, resp, uService.queryByName(userName), ufService);
	}
}
