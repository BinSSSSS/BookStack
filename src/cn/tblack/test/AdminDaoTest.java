package cn.tblack.test;

import org.junit.jupiter.api.Test;

import cn.tblack.dao.AdminDao;
import cn.tblack.dao.FactoryDao;

class AdminDaoTest {

	private AdminDao aDao =  FactoryDao.getAdminDao();
	
	@Test
	void testQueryByPassword() {
		
		System.out.println(aDao.queryByPassword("DAVID", "1395926989"));
		
	}

}
