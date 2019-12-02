package cn.tblack.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.tblack.filter.BackstageFilter;
import cn.tblack.model.Administrator;
import cn.tblack.model.BookUser;
import cn.tblack.model.UploadFile;
import cn.tblack.service.AdminService;
import cn.tblack.service.FactoryService;
import cn.tblack.service.UploadFileService;
import cn.tblack.service.UserService;
import cn.tblack.utils.CookieMap;
import cn.tblack.utils.CookieUtils;
import cn.tblack.utils.MD5Utils;
import cn.tblack.utils.UploadUtils;

/**
 * <span>后台管理员控制器</span>
 * 
 * @author TD唐登
 * @Date:2019年6月17日
 * @Version: 1.0(测试版)
 */

@WebServlet(urlPatterns = { "*.ado" })
public class BackstageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService uService = FactoryService.getUserService(); // 用户数据操作服务
	private AdminService aService = FactoryService.getAdminService(); // 管理员数据操作服务
	private UploadFileService ufService = FactoryService.getUploadFileService(); // 文件上传数据操作服务

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

		/* <span>使用反射技术来找到post请求的方法. 并调用该方法</span> */
		try {

			Method method = getClass().getDeclaredMethod(post, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @ 后台的操作，用于对用户进行删除操作
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void bsdelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* @验证请求对象是否是后台管理者,防止模拟请求发送 */
		if (!BackstageFilter.detectionRequest(req, resp)) {
			resp.sendError(404);
			return;
		}

		String username = req.getParameter("username");

		/* @ 删除指定的用户信息 */
		if (username != null) {

			uService.deleteByName(username);

			/* @发送数据到前台 */
			resp.getOutputStream().write(new String("Success").getBytes());
//			return;
		}

		/* @当用户直接通过链接进行点击的时候，那么就重定向到首页 */

	}

	/**
	 * @ backstage 登录请求的处理
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void bslogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		/* @ 传入的参数如果存在空数据的话,那么直接跳转到管理员的登录页面 */
		if (username == null || password == null) {
			forwardToBSL(req, resp);
			return;
		}

		/* @ 响应写入流 */
		ServletOutputStream out = resp.getOutputStream();

		/* @在管理员表中进行查询， 如果该用户能够找到并且密码等数据正确的时候，进行登陆 */
		Administrator admin = aService.queryByPassword(username, password);
		if (admin != null) {

			/* @在数据库找到了该管理员，设置Cookie */
			Cookie adminName = new Cookie("admin", admin.getAdminName());
			Cookie assid = new Cookie("assid", MD5Utils.encrypt(admin.getAdminName()));

			/* @ 设置管理员的cookie只能在BookStack/bs中使用 */
//			adminName.setPath(req.getContextPath() + "/bs/");
//			assid.setPath(req.getContextPath() + "/bs/");

			resp.addCookie(adminName);
			resp.addCookie(assid);

			/* @ 删除之前设置的错误提示 */
			req.removeAttribute("error_tips");

			/* @发送数据到前端，不进行重定向，允许其登录 */
			out.write(new String("APPROVE").getBytes());

			return;
		}

		System.out.println("设置了状态");

		/* @ 如果输入的数据不正确， 设置错误提示 */
		out.write(new String("用户名或密码错误!").getBytes());
	}

	/**
	 * @ 后台退出
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void bslogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* @ 如果存在cookie的话，清除之前设置的Cookie */
		Cookie[] cookies = req.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("admin") || cookie.getName().equals("assid")) {
				CookieUtils.delCookie(cookie, resp);
			}
		}
		/* @重定向到后台登录页面 */
		resp.sendRedirect(req.getContextPath() + "/bs_login.jsp");
	}

	/**
	 * @ 后台提交的数据查询请求- 将书栈的所有会员信息转发出去
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void bsquery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* @ 首先检验请求对象是否携带了Cookie对象，并且Cookie对象是否正确 */
		if (!BackstageFilter.detectionRequest(req, resp)) // 非正常请求， 可能是模拟请求，此时直接发送错误代码
		{
			resp.sendError(404);
			return;
		}

//		System.out.println("bsquery");

		/* @ 通过请求对象内的user name来进行模糊查询 */

		String username = req.getParameter("username");

		/* @ 只通过用户名来进行模糊查询 */
		List<BookUser> uList = uService.FuzzyQuery(username, null, null);

		if (uList != null) {
			req.setAttribute("user_list", uList);
		}

		/* @ 重定向到查询页面 */
		req.getRequestDispatcher("/bs/other-user-listing.jsp").forward(req, resp);
	}

	/**
	 * @ 文件上传的处理方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void bsuploadfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			CookieMap cMap = new CookieMap(req.getCookies());  //拿到Cookie组成的键值对
			String adminName = cMap.get("admin");

			
			if (adminName == null) {
				resp.getOutputStream().write("请登录之后再进行文件上传!".getBytes());
				return;
			}
			BookUser user = uService.queryByName(adminName);

			/*@ 进行文件的上传操作*/
			UploadUtils.upload(req, resp, user, ufService);
		}catch(Exception e) {
			resp.getWriter().print(e.getMessage());
		}
		
	}

	/**
	 * @ 在数据库中查询上传文件的信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryUploadFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		/* @ 首先检验请求对象是否携带了Cookie对象，并且Cookie对象是否正确 */
		if (!BackstageFilter.detectionRequest(req, resp)) // 非正常请求， 可能是模拟请求，重定向到登录页面
		{
			resp.sendRedirect(req.getContextPath() + "/bs_login.jsp");
			return;
		}
		
		System.out.println("queryUploadFile");
		
		/*@在上传文件的数据库中拿到所有的数据并返回到request对象中*/
		List<UploadFile> upFiles = ufService.getAll();
		
		req.setAttribute("uploadfiles", upFiles);
		
		if(upFiles == null ||  upFiles.isEmpty())
		{
			req.setAttribute("hasFiles", false);
		}else{
			req.setAttribute("hasFiles", true);
		}
		
		/*@ 进行转发到页面*/
		req.getRequestDispatcher("/bs/table.jsp").forward(req, resp);
		

	}
	
	/**
	 * @ 上传文件的删除操作， 需要删除数据表和本地的文件
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void upfileDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* @ 首先检验请求对象是否携带了Cookie对象，并且Cookie对象是否正确 */
		if (!BackstageFilter.detectionRequest(req, resp)) // 非正常请求， 可能是模拟请求，重定向到登录页面
		{
			resp.sendRedirect(req.getContextPath() + "/bs_login.jsp");
			return;
		}
		
		/*@拿到该文件对象信息*/
		UploadFile upFile = ufService.query(Integer.parseInt(req.getParameter("file_id")));
		
		
		if(upFile != null) {
			File file =  new File(upFile.getSavePath() + "/" +upFile.getRealName());
			
			System.out.println(file.getAbsolutePath());
			
			//首先进行文件的删除， 如果文件删除成功，那么也进行数据库的删除
			if(file.delete()) {
				
				ufService.delete(upFile.getFileId());  //进行数据库的删除
				System.out.println("删除成功!");

				resp.getWriter().print("Success");
				return;
			}
		}
		resp.getWriter().print("failed");
	}
	/**
	 * @ 上传文件的下载操作，需要将请求的文件id的相对路径发送回前端
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void upfileDownload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* @ 首先检验请求对象是否携带了Cookie对象，并且Cookie对象是否正确 */
		if (!BackstageFilter.detectionRequest(req, resp)) // 非正常请求， 可能是模拟请求，重定向到登录页面
		{
			resp.sendRedirect(req.getContextPath() + "/bs_login.jsp");
			return;
		}
		System.out.println("upfileDownload");
		/*@拿到该文件对象信息*/
		
		try {
			UploadFile upFile = ufService.query(Integer.parseInt(req.getParameter("file_id")));
			if(upFile != null) {
				
				String savePath  = upFile.getSavePath();
				savePath =  savePath.substring(savePath.indexOf("BookStack") + "BookStack".length());
				String url = req.getRequestURL().toString();
				
				/*@ 在结尾的位置加上/*/
				if(!savePath.isEmpty())
					savePath += "/";
				url =  url.substring(0,url.lastIndexOf("/"));
				/*@拼接为下载URL*/
				String dwUrl  = url +  "/" + savePath  + upFile.getRealName();
				
				System.out.println();
				System.out.println(dwUrl);
				
				resp.getWriter().print(dwUrl);
				return;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		resp.getWriter().print("404");
	}
	
	/**
	 * @ 转发到 管理员登录页面
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forwardToBSL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/bs_login.jsp").forward(req, resp);
	}
}
