package cn.tblack.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * <span>用于与数据库链接的工具类。 使用 c3p0中的数据库池来返回连接对象</span>
 * @author TD唐登
 * @Date:2019年6月6日
 * @Version: 1.0(测试版)
 */
public class JDBCUtils {

	/* <span>保存着数据库的链接对象</span>*/
	private static DataSource dataSource  = null;  
	
	static {	//使用静态代码区进行初始化对象- 对象将在类被加载的时候初始化
		dataSource =  new ComboPooledDataSource("oracle");	//拿到 c3p0中的数据库池对象- (以oracle命名的数据对象)
	}
	
	/* <span>拿到oracle数据库的连接对象</span>*/
	public static Connection getOrclConnection() throws SQLException {
		
		return dataSource.getConnection();
		
	}
	
	
	/**
	 * 关闭拿到的连接对象- 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if(conn == null ) return;
		try {
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 事务回滚
	 */
	public static void rollback(Connection conn) {
		
		if(conn == null ) return;
		try {
			conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 事务提交
	 */
	public static void commit(Connection conn) {
		if(conn == null ) return;
		try {
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
