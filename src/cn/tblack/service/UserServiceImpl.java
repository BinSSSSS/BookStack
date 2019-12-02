package cn.tblack.service;

import java.util.List;

import cn.tblack.dao.FactoryDao;
import cn.tblack.dao.UserDao;
import cn.tblack.model.BookUser;


/**
 * <span>服务层实现类- 该类为代理设计模式, 将大部分的操作都转交了 BookUserDao进行实际操作</span>
 * @author TD唐登
 * @Date:2019年6月11日
 * @Version: 1.0(测试版)
 */
public class UserServiceImpl implements UserService {

	private UserDao uDao =  FactoryDao.getUserDao();

	@Override
	public boolean insert(BookUser u) {
		return uDao.insert(u);
	}

	@Override
	public boolean insert(BookUser u, boolean transaction) {
		return uDao.insert(u, transaction);
	}

	@Override
	public int deleteById(int id) {
		return uDao.deleteById(id);
	}

	@Override
	public int deleteById(int id, boolean transaction) {
		return uDao.deleteById(id, transaction);
	}

	@Override
	public int deleteByName(String name, boolean transaction) {
		return uDao.deleteByName(name,transaction);
	}

	@Override
	public int deleteByName(String name) {
		return uDao.deleteByName(name);
	}

	@Override
	public int update(BookUser u, int id) {
		return uDao.update(u, id);
	}

	@Override
	public int update(BookUser u, int id, boolean transaction) {
		return uDao.update(u, id, transaction);
	}

	@Override
	public long count(int id) {
		return uDao.count(id);
	}

	@Override
	public long count(String name) {
		return uDao.count(name);
	}

	@Override
	public long count() {
		return uDao.count();
	}

	@Override
	public BookUser queryById(int id) {
		return uDao.queryById(id);
	}

	@Override
	public BookUser queryByName(String name) {
		return uDao.queryByName(name);
	}

	@Override
	public BookUser queryByName(String name, boolean transaction) {
		return uDao.queryByName(name,transaction);
	}

	@Override
	public BookUser queryById(int id, boolean transaction) {
		return uDao.queryById(id, transaction);
	}
	
	@Override
	public BookUser queryByPassword(String account, String password) {
		return uDao.queryByPassword(account, password);
	}

	@Override
	public List<BookUser> getAll() {
		return uDao.getAll();
	}

	@Override
	public List<BookUser> getAll(boolean transaction) {
		return uDao.getAll(transaction);
	}

	@Override
	public List<BookUser> FuzzyQuery(String BookUserName, String address, String phoneNum) {
		return uDao.FuzzyQuery(BookUserName, address, phoneNum);
	}

	@Override
	public List<BookUser> FuzzyQuery(String BookUserName, String address, String phoneNum, boolean transaction) {
		return uDao.FuzzyQuery(BookUserName, address, phoneNum,transaction);
	}

	@Override
	public long countByAccount(long account) {
		return uDao.countByAccount(account);
	}

	@Override
	public BookUser queryByAccount(long account) {
		return uDao.queryByAccount(account);
	}

	@Override
	public long countByPhone(long phone) {
		return uDao.countByPhone(phone);
	}

	@Override
	public BookUser queryByPhone(long phone) {
		return uDao.queryByPhone(phone);
	}

	
}
