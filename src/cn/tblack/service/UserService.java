package cn.tblack.service;

import java.util.List;

import cn.tblack.model.BookUser;

/**
 * <span>服务层接口-用于制定BookUser对象的服务方法。</span>
 * @author TD唐登
 * @Date:2019年6月11日
 * @Version: 1.0(测试版)
 */
public interface UserService {

	/**
	 * <span>插入数据到数据表表中</span>
	 * @param u 插入的数据信息
	 * @return 插入是否成功
	 */
	public boolean insert(BookUser u);

	/**
	 * <span>插入数据到数据表表中, 是否使用 事务处理</span>
	 * @param u  插入的数据信息
	 * @param transaction 是否使用事务处理
	 * @return 插入是否成功
	 */
	public boolean insert(BookUser u, boolean transaction);

	/**
	 * @ 删除指定id的行
	 * @param id
	 * @return 删除的行数
	 */
	public int deleteById(int id);

	/**
	 * @ 通用的删除操作, 目的是为了更好的支持事务处理
	 * @param sql  DML操作语句
	 * @param args 传递的参数
	 * @param transaction  是否使用事务处理
	 * @return 返回删除数据的行数
	 */
//	public  int delete(String sql,boolean transaction,Object...args);
	
	/**
	 * @ 删除指定id的行
	 * @param id
	 * @param transaction 是否需要事务处理
	 * @return
	 */
	public int deleteById(int id, boolean transaction);
	
	/**
	 * @ 删除指定名字的全部用户
	 * @param name 需要删除的用户名
	 * @param transaction 是否需要事务处理
	 * @return 删除的用户数量
	 */
	public int deleteByName(String name, boolean transaction);
	
	/**
	 * @ 删除指定名字的全部用户
	 * @param name 需要删除的用户名
	 * @return 删除的用户数量
	 */
	public int deleteByName(String name);

	/**
	 * 更新指定id用户的信息
	 * @param u  更新的数据
	 * @param id 需要更新的用户id
	 * @return 返回更新的行数
	 */
	public int update(BookUser u, int id);


	/**
	 * @ 更加通用的更新方法, 目的是为了支持事务处理
	 * @param sql DML语句
	 * @param transaction 是否使用事务处理
	 * @param args 传递给SQL语句的参数
	 * @return 返回受影响的行数
	 */
//	public int update(String sql, boolean transaction, Object...args);

	/**
	 * 更新操作
	 * @param sql DML语句
	 * @param transaction 是否使用事务处理
	 * @param args 传递给SQL语句的参数
	 * @return 返回受影响的行数
	 */
	public int update(BookUser u , int id, boolean transaction);	
	
	/**
	 * 拿到该id用户信息的数量-
	 * @param id 需要查找的id
	 * @return 返回该id的数量
	 */
	public long count(int id);

	/**
	 * 返回该姓名用户的数量
	 * 
	 * @param name 用户名
	 * @return 返回指定用户名的数量
	 */
	public long count(String name);

	/**
	 * 计算数据表的全部数据
	 * @return 返回该数据表内的全部数据
	 */
	public long count();

	/**
	 * @ 查询指定账户的条数
	 * @return
	 */
	public long countByAccount(long account);
	/**
	 * @ 查询指定手机号的条数
	 * @return
	 */
	public long countByPhone(long phone);
	/**
	 * @ 根据用户的账户来查询用户信息
	 * @param account
	 * @return
	 */
	public BookUser queryByAccount(long account);
	/**
	 * 返回指定id的第一条数据
	 * @param id
	 * @return
	 */
	public BookUser queryById(int id);

	/**
	 * @param name 需要查找的用户名
	 * @return 返回该用户名的所有用户信息
	 */
	public BookUser queryByName(String name);

	/**
	 * @ 是否以事务处理的方式进行查询
	 * @param name 需要查询的用户名
	 * @param transaction 是否以事务处理
	 * @return 返回查询结果的列表
	 */
	public BookUser queryByName(String name, boolean transaction);
	
	/**
	 * @ 是否使用事务处理来查询对象
	 * @param id 需要查询的id
	 * @param transaction 是否使用事务处理
	 * @return 返回查询到的用户对象
	 */
	public BookUser queryById(int id, boolean transaction) ;
	
	/**
	 * @ 根据用户的手机号来查询用户信息
	 * @param account
	 * @return
	 */
	public BookUser queryByPhone(long phone);
	/**
	 * @ 通过用户名和密码来查询数据库内的数据
	 * @param BookUserName  传递的用户名
	 * @param password  传递的该用户的密码
	 * @return  用户对象， 或者空对象
	 */
	public BookUser queryByPassword(String account, String password);
	
	
	/**
	 * @ 模糊查询, 根据提供的用户名、地址、手机号进行查询- 单个对象可以为空
	 * @param BookUserName
	 * @param homeLand
	 * @param phoneNum
	 * @return 返回模糊查询的结果集
	 */
	public List<BookUser> FuzzyQuery(String BookUserName,String homeLand, String phoneNum);
	
	/**
	 * @ 模糊查询, 根据提供的用户名、地址、手机号进行查询- 单个对象可以为空
	 * @param BookUserName
	 * @param homeLand
	 * @param phoneNum
	 * @param transaction 是否进行事务处理
	 * @return 返回模糊查询的结果集
	 */
	public List<BookUser> FuzzyQuery(String account,String homeLand, String phoneNum, boolean transaction);
	
	
	
	/**
	 * @return 返回数据表内的全部数据
	 */
	public List<BookUser> getAll();
	
	/**
	 * @ 拿到表中的所有数据
	 * @param transaction 是否以事务的方式进行查询
	 * @return 返回表中的所有数据
	 */
	public List<BookUser> getAll(boolean transaction );
}
