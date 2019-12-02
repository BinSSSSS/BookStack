package cn.tblack.service;

import java.util.List;

import cn.tblack.dao.AdminDao;
import cn.tblack.dao.FactoryDao;
import cn.tblack.model.Administrator;

public class AdminServiceImpl implements AdminService{

	
	private AdminDao aDao =  FactoryDao.getAdminDao();
	
	@Override
	public boolean insert(Administrator u) {
		return aDao.insert(u);
	}

	@Override
	public boolean insert(Administrator u, boolean transaction) {
		return aDao.insert(u);
	}

	@Override
	public int deleteById(int id) {
		return aDao.deleteById(id);
	}

	@Override
	public int deleteById(int id, boolean transaction) {
		return aDao.deleteById(id,transaction);
	}

	@Override
	public int deleteByName(String name, boolean transaction) {
		return aDao.deleteByName(name,transaction);
	}

	@Override
	public int deleteByName(String name) {
		return aDao.deleteByName(name);
	}

	@Override
	public int update(Administrator u, int id) {
		return aDao.update(u, id);
	}

	@Override
	public int update(Administrator u, int id, boolean transaction) {
		return aDao.update(u, id, transaction);
	}

	@Override
	public long count(int id) {
		return aDao.count(id);
	}

	@Override
	public long count(String name) {
		return aDao.count(name);
	}

	@Override
	public long count() {
		return aDao.count();
	}

	@Override
	public long countByAccount(long account) {
		return aDao.countByAccount(account);
	}

	@Override
	public long countByPhone(long phone) {
		return aDao.countByPhone(phone);
	}

	@Override
	public Administrator queryById(int id) {
		return aDao.queryById(id);
	}

	@Override
	public Administrator queryByName(String name) {
		return aDao.queryByName(name);
	}

	@Override
	public Administrator queryByName(String name, boolean transaction) {
		return aDao.queryByName(name, transaction);
	}

	@Override
	public Administrator queryById(int id, boolean transaction) {
		return aDao.queryById(id, transaction);
	}

	@Override
	public Administrator queryByPhone(long phone) {
		return aDao.queryByPhone(phone);
	}

	@Override
	public Administrator queryByAccount(long account) {
		return aDao.queryByAccount(account);
	}

	@Override
	public Administrator queryByPassword(String account, String password) {
		return aDao.queryByPassword(account, password);
	}

	@Override
	public List<Administrator> FuzzyQuery(String AdminName, String address, String phoneNum) {
		return aDao.FuzzyQuery(AdminName, address, phoneNum);
	}

	@Override
	public List<Administrator> FuzzyQuery(String AdminName, String address, String phoneNum, boolean transaction) {
		return aDao.FuzzyQuery(AdminName, address, phoneNum, transaction);
	}

	@Override
	public List<Administrator> getAll() {
		return aDao.getAll();
	}

	@Override
	public List<Administrator> getAll(boolean transaction) {
		return aDao.getAll(transaction);
	}

}
