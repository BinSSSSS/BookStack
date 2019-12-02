package cn.tblack.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cn.tblack.dao.FactoryDao;
import cn.tblack.dao.UploadFileDao;

class UploadFileDaoTest {

	
	private UploadFileDao ufDao = FactoryDao.getUploadFileDao();
	
	@Test
	void test() {
//		System.out.println( ufDao.count(1));
		System.out.println(ufDao.query(1));
	}

}
