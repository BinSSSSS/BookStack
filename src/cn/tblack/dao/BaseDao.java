package cn.tblack.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.tblack.utils.JDBCUtils;

/**
 * <span>泛型的通用DAO底层实现- 实现了DAO层的通用操作. 使用了DBUtils工具包内的QueryRunner来具体实现</span>
 * <span>适配器设计模式</span>
 * @author TD唐登
 * @Date:2019年6月10日
 * @Version: 1.0(测试版)
 * @param <T>
 */
public abstract   class BaseDao<T> {

	private Class<T> clazz;  //保存着当前类的具体类型
	private QueryRunner queryRunner  = new QueryRunner();	//使用可插入策略执行SQL查询以处理结果集的类。
	
	public BaseDao() {
		
		/* <span>在调用构造函数的时候则初始化当前的类类型</span>*/
		initClassType();
		
	}
	
	/**
	 * <span>初始化classType对象。 将该类类型设置为泛型的类型</span>
	 */
	@SuppressWarnings("unchecked")
	private void initClassType() {
		try {
			
			/* <span>返回Type表示此所表示的实体（类，接口，基本类型或void）的直接超类</span>*/
			Type type = this.getClass().getGenericSuperclass();  //注意- 如果是在超类本身调用该函数， 则返回Object

			/*<span>ParameterizedType表示一个参数化类型，如Collection <String>。</span>*/
			if(type instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType)type;
				
				/*<span>拿到的Type数组的第一个元素为T的具体类型</span>*/
				clazz = (Class<T>) pt.getActualTypeArguments()[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * <span>通过SQL语句得到该查询到的对象， 不支持事务处理</span>
	 * <span>通过dbutils中的QueryRunner来简化查询</span>
	 * @param sql
	 * @return
	 */
	public T query(String sql, Object... args) {
		
		Connection conn = null;
		T value =  null;
		try {
			conn = JDBCUtils.getOrclConnection(); //拿到连接对象	
			value = this.query(conn, sql, args);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return value;
	}
	/**
	 * @ 通用的查询操作, 查询到对象的值并包装为T类型进行返回- 可以支持事务处理
	 * @param conn 数据库的连接对象
	 * @param sql	DML语句
	 * @param args	传递的参数
	 * @return	返回SQL查询结果
	 */
	public T query(Connection conn,String sql, Object... args) {
		
		
		T entirety  = null;
		try {	
			/*<span>使用了BeanHandler将会自动将表内的字段名和传递的ClassType中声明的字段值相对应(需要保证表中的字段名和类中的成员名相同)</span>*/
			entirety = queryRunner.query(conn, sql,new BeanHandler<>(clazz), args); //通过查询语句来获取结果
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return entirety;
	}
	
	/**
	 * @  拿到表中的指定的多条记录 - 不支持事务处理
	 * @param sql	DML语句
	 * @param args	传递的参数
	 * @return   SQL查询的结果
	 */
	public List<T> getList(String sql, Object... args){
		
		Connection conn  = null;
		List<T> list =  null;
		try {
			conn = JDBCUtils.getOrclConnection();	//拿到连接对象	
			list = this.getList(conn, sql, args);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return list;
	}
	
	/**
	 * @ 使用指定的Connection对象,执行SQL语句拿到表中指定的多条记录, 可以支持事务处理
	 * @param conn 数据库的连接对象
	 * @param sql	DML语句
	 * @param args	传递的参数
	 * @return	返回SQL查询结果
	 */
	public List<T> getList(Connection conn,String sql, Object... args){
		
		List<T> valueList = null;
		try {
			valueList = queryRunner.query(conn, sql,new BeanListHandler<>(clazz),args);  //返回某个结果的List
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return valueList;
	}
	
	/**
	 * @ 通用的操作， 可以作为 update、insert、delete使用 - 未支持事务处理
	 * @param sql 执行的SQL语句
	 * @param args 传递的参数列表
	 * @return 返回受影响的行数
	 */
	public int update(String sql, Object...args) {
		
		Connection conn = null;
		int rows = 0;
		try {
			conn = JDBCUtils.getOrclConnection();
			rows = this.update(conn, sql, args);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.closeConnection(conn);
		}
		
		return rows;
	}

	/**
	 * @ 通用的DML操作， 传递指定的Connection对象来进行操作
	 * @param conn 数据库的连接对象
	 * @param sql	DML语句
	 * @param args	传递的参数
	 * @return	返回受影响的行数
	 */
	public int update(Connection conn, String sql, Object...args) {
		
		int rows = 0;
		try {
			rows =  queryRunner.update(conn,sql,args);
		}catch(Exception e) {
//			JDBCUtils.rollback(conn);  //发生异常之后回滚数据。
			e.printStackTrace();
		}
		
		return rows;
	}
	
	
	/**
	 * @ 通用的方法， 通过SQL语句来返回某些单个的查询结果对象。 不支持事务处理
	 * @param sql  需要指定SQL语句
	 * @param args SQL语句使用到的参数
	 * @return	SQL语句的结果
	 */
	public Object getValue(String sql, Object...args) {
		Connection conn = null;
		Object obj = null;
		try {
			conn  = JDBCUtils.getOrclConnection();
			obj = getValue(conn, sql, args);
		}catch(Exception e) {
			e.printStackTrace( );
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		return obj;
	}
	
	/**
	 * @ 传递指定的Connection对象来指定SQL操作， 并返回该操作返回的对象- 可以支持事务处理
	 * @param conn 传递的连接对象
	 * @param sql	需要指定SQL语句
	 * @param args  SQL语句使用到的参数
	 * @return	SQL语句的结果
	 */
	public Object getValue(Connection conn,String sql, Object...args) {

		Object obj = null;
		
		try {
			/*<span>通过使用标量处理来获取单个对象</span>*/
			obj  = queryRunner.query(conn, sql, new ScalarHandler<>(), args);
		}catch(Exception e) {
			e.printStackTrace( );
		}
		
		return obj;
	}
	
}
