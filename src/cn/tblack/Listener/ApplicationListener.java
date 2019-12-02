package cn.tblack.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.tblack.utils.UploadPropertiesMap;

/**
 * <span>Web服务器启动时的监听类，主要是用于初始化一些参数</span>
 * @author TD唐登
 * @Date:2019年6月21日
 * @Version: 1.0(测试版)
 */
@WebListener
public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	
		/* @每次启动服务器的时候为其设置文件上传的绝对路径，因为初始默认为空 
		 * @并且每次启动的时候进行类加载， 之后使用UploadPropertiesMap的时候类已经保存了所有的配置信息 */
		
		UploadPropertiesMap.getInstance().put("absPath", sce.getServletContext().getRealPath("/uploadfiles/"));
		UploadPropertiesMap.getInstance().put("adminPath", sce.getServletContext().getRealPath("./"));
		System.out.println(UploadPropertiesMap.getInstance());
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

}
