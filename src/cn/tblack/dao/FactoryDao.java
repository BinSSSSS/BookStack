package cn.tblack.dao;

/**
 * <span>工厂类， 产生实现了DAO接口的类</span>
 * @author TD唐登
 * @Date:2019年6月21日
 * @Version: 1.0(测试版)
 */
public class FactoryDao {
	
	/**
	 * @ 返回文件上传信息的DAO
	 * @return
	 */
	public static UploadFileDao getUploadFileDao() {
		return new UploadFileDaoImpl();
	}
	
	/**
	 * @ 返回管理员信息的DAO
	 * @return
	 */
	public static AdminDao  getAdminDao() {
		return new AdminDaoImpl();
	}
	
	/**
	 * @ 返回用户信息的DAO层
	 * @return
	 */
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
}
