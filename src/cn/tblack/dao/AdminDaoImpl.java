package cn.tblack.dao;

import java.util.List;

import cn.tblack.model.Administrator;
import cn.tblack.utils.NumberFormat;

/**
 * <span>管理员数据库操作的实现类</span>
 * @author TD唐登
 * @Date:2019年6月17日
 * @Version: 1.0(测试版)
 */
public class AdminDaoImpl  extends BaseDao<Administrator> implements AdminDao{

	@Override
	public boolean insert(Administrator u) {
		
		String sql = "INSERT INTO book_admin(admin_name) VALUES(?)";
		
		return super.update(sql, u.getAdminName()) > 0;
	}

	@Override
	public boolean insert(Administrator u, boolean transaction) {
		return false;
	}

	@Override
	public int deleteById(int id) {
		String sql = "DELETE book_admin WHERE admin_id=?";
		
		return super.update(sql,id);
	}

	@Override
	public int deleteById(int id, boolean transaction) {
		return 0;
	}

	@Override
	public int deleteByName(String name, boolean transaction) {
		String sql =  "DELETE book_admin WHERE admin_name=?";
		
		return super.update(sql, name);
	}

	@Override
	public int deleteByName(String name) {
		return 0;
	}

	@Override
	public int update(Administrator u, int id) {
		String sql = "UPDATE book_admin set admin_name=? WHERE admin_id=?";
		
		return super.update(sql,u.getAdminName(),id);
	}

	@Override
	public int update(Administrator u, int id, boolean transaction) {
		return 0;
	}


	
	@Override
	public long count(int id) {
		String sql = "SELECT COUNT(admin_name) FROM book_admin WHERE admin_id=?";
		
		
		return NumberFormat.getNum(super.getValue(sql, id));
	}

	@Override
	public long count(String name) {
		String sql = "SELECT COUNT(admin_name) FROM book_admin WHERE admin_name=?";
		
		return NumberFormat.getNum(super.getValue(sql,name));
	}

	@Override
	public long count() {
		String sql = "SELECT COUNT(admin_id) FROM book_admin";
		return NumberFormat.getNum(super.getValue(sql));
	}

	@Override
	public long countByAccount(long account) {
		return 0;
	}

	@Override
	public long countByPhone(long phone) {
		return 0;
	}

	@Override
	public Administrator queryById(int id) {
		String sql = "SELECT admin_id adminId, admin_name adminName, authorizer, authorizer_time authorizerTime"
				+ " FROM book_admin WHERE admin_id=?";
		
		return  super.query(sql, id);
	}

	@Override
	public Administrator queryByName(String name) {
		
		String sql = "SELECT admin_id adminId, admin_name adminName, authorizer, authorizer_time authorizerTime"
				+ " FROM book_admin WHERE admin_name=?";
		
		return super.query(sql, name);
	}

	@Override
	public Administrator queryByName(String name, boolean transaction) {
		return null;
	}

	@Override
	public Administrator queryById(int id, boolean transaction) {
		return null;
	}

	@Override
	public Administrator queryByPhone(long phone) {
		return null;
	}

	@Override
	public Administrator queryByAccount(long account) {
		return null;
	}

	@Override
	public Administrator queryByPassword(String name, String password) {
		
		String sql = "SELECT admin_id adminId, admin_name adminName, authorizer, authorizer_time authorizerTime  "
				+ " FROM book_admin, book_user bu "
				+ "WHERE admin_name=? AND  bu.password=? AND bu.userName=admin_name";
		
		return super.query(sql, name,password);
	}

	@Override
	public List<Administrator> FuzzyQuery(String AdminName, String address, String phoneNum) {
		return null;
	}

	@Override
	public List<Administrator> FuzzyQuery(String AdminName, String address, String phoneNum, boolean transaction) {
		return null;
	}

	@Override
	public List<Administrator> getAll() {
		
		String sql =  "SELECT admin_id adminId, admin_name adminName, authorizer, "
					+ "authorizer_time authorizerTime FROM ADMIN";
		
		return super.getList(sql);
	}

	@Override
	public List<Administrator> getAll(boolean transaction) {
		return null;
	}

}
