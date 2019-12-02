package cn.tblack.utils;

import java.sql.Connection;

/**
 * <span>包含一个带事务提交的Connection对象-避免重复操作</span>
 * @author TD唐登
 * @Date:2019年6月11日
 * @Version: 1.0(测试版)
 */
public class TransactionConnection  {
	
	private Connection conn ;
	
	public TransactionConnection() {
		
		try {
			conn =  JDBCUtils.getOrclConnection();
			conn.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void commit() {
		JDBCUtils.commit(conn);
	}
	
	public Connection getConnetion() {
		return conn;
	}
	public void close() {
		JDBCUtils.closeConnection(conn);
	}
}
