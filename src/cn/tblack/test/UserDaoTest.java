package cn.tblack.test;


import org.junit.jupiter.api.Test;

import cn.tblack.dao.FactoryDao;
import cn.tblack.dao.UserDao;

class UserDaoTest {

	private UserDao uDao = FactoryDao.getUserDao();
	
	@Test
	void testQueryByPassword() {
		
		
		System.out.println(uDao.queryByPassword("6899000297", "12345678"));
		
	}
	
	@Test void testDelByName() {
		System.out.println(uDao.deleteByName("Jan"));
//		System.out.println(uDao.queryByName("李四"));
//		System.out.println(uDao.deleteById(1));
	}

}
