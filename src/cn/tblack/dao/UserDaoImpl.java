package cn.tblack.dao;

import java.util.List;

import cn.tblack.model.BookUser;
import cn.tblack.utils.NumberFormat;
import cn.tblack.utils.FactoryConnection;
import cn.tblack.utils.TransactionConnection;

/**
 * <span>UserDao的具体实现类。 </span>
 * @author TD唐登
 * @Date:2019年6月10日
 * @Version: 1.0(测试版)
 */
public class UserDaoImpl extends BaseDao<BookUser> implements UserDao {

	@Override
	public boolean insert(BookUser u) {
		return insert(u, false);
	}

	@Override
	public boolean insert(BookUser u, boolean transaction) {

		/*@ 只插入表中非空数据*/
		String sql = "INSERT INTO book_user(account,userName, password,phoneNum) " + 
				"VALUES(?,?,?,?)";

		/* <span>返回插入是否成功。 检测影响的行数是否大于0</span> */

		/* <span>非事务处理的时候直接进行返回</span> */
		if (!transaction) {

			return super.update(sql, u.getNecessaryParam()) > 0;
		}

		/* <span>开始事务处理</span> */
		TransactionConnection transaConn = FactoryConnection.getTransactionConnection();
		boolean success = false;

		success = super.update(transaConn.getConnetion(), sql, u.getNecessaryParam()) > 0;

		transaConn.commit();
		transaConn.close();

		return success;
	}

	@Override
	public List<BookUser> getAll(boolean transaction) {
		String sql = "SELECT * FROM book_user";

		if(!transaction)
			return super.getList(sql);
		
		TransactionConnection transConn = FactoryConnection.getTransactionConnection();
		
		List<BookUser> list  = super.getList(transConn.getConnetion(), sql);
		
		transConn.commit();
		transConn.close();
		
		return list;
	}

	@Override
	public List<BookUser> getAll() {
		return getAll(false);
	}
	
	@Override
	public int deleteById(int id) {
		return deleteById(id,false);
	}
	@Override
	public int deleteById(int id, boolean transaction) {
		String sql = "DELETE book_user WHERE USERID=?";
		
		if(!transaction)
			return super.update(sql, id);
		
		TransactionConnection transConn =  FactoryConnection.getTransactionConnection();
		int rows = super.update(transConn.getConnetion(), sql,id);
		
		transConn.commit();
		transConn.close();
		return rows;
	}

	@Override
	public int deleteByName(String name) {
		return deleteByName(name, false);	
	}
	@Override
	public int deleteByName(String name, boolean transaction) {
		String sql = "DELETE book_user WHERE USERNAME=?";
		
		if(!transaction)
			return super.update(sql, name);
		
		
		TransactionConnection transConn  = FactoryConnection.getTransactionConnection();
		int rows =  super.update(transConn.getConnetion(), sql, name);
		
		transConn.commit();
		transConn.close();
		return rows;
	}

	@Override
	public int update(BookUser u, int id) {
		return update(u, id, false);
	}

	@Override
	public int update(BookUser u, int id, boolean transaction) {

		String sql = "UPDATE book_user  SET userName=?,password=?, gender=?,brithday=?,homeLand=?," + 
				"age=?,personalSignature=?,job=?,email=? WHERE USERID=?";
		
		Object[] params =  u.getMutableParam();
		if (!transaction)
			return super.update(sql,params,id);
	
		/*<span>开始事务处理</span>*/
		TransactionConnection transConn = FactoryConnection.getTransactionConnection();
		
		int rows =  super.update(transConn.getConnetion(), sql, params,id);
		
		transConn.commit();
		transConn.close();
		return rows;
	}

	
	
	@Override
	public long count(int id) {
		String sql = "SELECT COUNT(USERID) FROM book_user WHERE USERID=?";

		/* <span>使用Count返回的对象为BigDecimal对象。 </span> */
		Object obj = super.getValue(sql,id);	
		return NumberFormat.getNum(obj);	
	}

	@Override
	public long count(String name) {

		String sql = "SELECT COUNT(USERID) FROM book_user WHERE USERNAME=?";

		/* <span>返回的对象为 BigDecimal， 需要调用函数获取到long值</span> */
		Object obj = super.getValue(sql,name);
		
		return NumberFormat.getNum(obj);	
	}

	@Override
	public long count() {
		String sql = "SELECT COUNT(USERID) FROM book_user";

		/* <span>返回的对象为 BigDecimal， 需要调用函数获取到long值</span> */
		Object obj = super.getValue(sql);
			
		return NumberFormat.getNum(obj);	
	}
	@Override
	public long countByAccount(long account) {
		String sql = "SELECT COUNT(USERID)  FROM book_user WHERE account=?";
		
		Object obj =  super.getValue(sql, account);
		
		return NumberFormat.getNum(obj);
	}
	@Override
	public long countByPhone(long phone) {
		String sql = "SELECT COUNT(USERID)  FROM book_user WHERE phoneNum=?";
		
		Object obj =  super.getValue(sql, phone);
		
		return NumberFormat.getNum(obj);
	}
	
	
	@Override
	public BookUser queryById(int id) {

		return queryById(id, false);
	}

	@Override
	public BookUser queryById(int id, boolean transaction) {

		String sql = "SELECT * FROM book_user WHERE USERID=?";

		/* <span>非事务处理</span> */
		if (!transaction)
			return super.query(sql, id);

		/* <span>事务处理</span> */
		BookUser u = null;
		TransactionConnection transConn = FactoryConnection.getTransactionConnection();

		super.query(transConn.getConnetion(), sql, id);

		/* <span>事务提交和关闭</span> */
		transConn.commit();
		transConn.close();
		return u;
	}

	@Override
	public BookUser queryByPassword(String account, String password) {
		
		String sql = "SELECT * FROM book_user WHERE ACCOUNT=? AND PASSWORD=?";
	
		return super.query(sql, account, password);
	}
	
	
	@Override
	public BookUser queryByName(String name) {

		return queryByName(name, false);
	}
	@Override
	public BookUser queryByName(String name, boolean transaction) {
		String sql = "SELECT * FROM book_user WHERE USERNAME=?";

		if (!transaction)
			return super.query(sql, name);

		/* <span>事务处理</span> */
		TransactionConnection transConn = FactoryConnection.getTransactionConnection();

		BookUser user = super.query(transConn.getConnetion(), sql, name);

		/* <span>事务提交与关闭</span> */
		transConn.commit();
		transConn.close();

		return user;
	}

	@Override
	public BookUser queryByAccount(long account) {
		String sql = "SELECT * FROM book_user WHERE account=?";
		
		return super.query(sql, account);
	}
	@Override
	public BookUser queryByPhone(long phone) {
		String sql = "SELECT * FROM book_user WHERE phoneNum=?";
		
		return super.query(sql, phone);
	}
	
	@Override
	public List<BookUser> FuzzyQuery(String userName, String address, String phoneNum) {
		
		return FuzzyQuery(userName,address,phoneNum,false);
	}
	
	@Override
	public List<BookUser> FuzzyQuery(String userName, String homeLand, String phoneNum, boolean transaction) {
		/*@ 确保当用户没有传递任何数据的时候查询到表内的全部数据*/
		String sql = "SELECT * FROM book_user WHERE 1=1 ";
	
		/*@ SQL查询命令*/
		StringBuilder comm =  new StringBuilder(sql);
		
		/*@ 根据不同的对象追加不同的SQL模糊查询语句*/
		if(userName != null && !userName.isEmpty()) {
			comm.append("AND USERNAME LIKE '%" + userName +"%'");
		}
		if(homeLand != null && !homeLand.isEmpty()) {
			comm.append("AND HOMELAND LIKE '%" + homeLand +"%'");
		}
		if(phoneNum != null && !phoneNum.isEmpty()) {
			comm.append("AND PHONENUM LIKE '%" + phoneNum +"%'");
		}
		
		
		if(!transaction)
			return super.getList(comm.toString());
		
		TransactionConnection transaConn =  FactoryConnection.getTransactionConnection();
		
		List<BookUser> list = super.getList(transaConn.getConnetion(),comm.toString());
		
		transaConn.commit();
		transaConn.close();
		
		return list;
	}

	
}
