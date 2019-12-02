package cn.tblack.service;

/**
 * <span>用户创建用户服务类的工厂类</span>
 * @author TD唐登
 * @Date:2019年6月11日
 * @Version: 1.0(测试版)
 */
public class FactoryService {

	public static UserService getUserService() {
		return new UserServiceImpl();
	}
	
	public static AdminService getAdminService() {
		return new AdminServiceImpl();
	}

	public static UploadFileService getUploadFileService() {
		return new UploadFileServiceImpl();
	}
}
