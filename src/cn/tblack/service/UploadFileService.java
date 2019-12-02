package cn.tblack.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.tblack.model.BookUser;
import cn.tblack.model.UploadFile;

public interface UploadFileService {
	/**
	 * @ 将上传文件的信息插入数据库中,不支持事务处理
	 * @param file
	 * @return 返回是否插入成功
	 */
	public boolean insert(UploadFile file);
	
	/**
	 * @ 将上传文件的信息插入数据库中,支持事务处理
	 * @param file 
	 * @param conn
	 * @return 返回是否插入成功
	 */
	public boolean insert(UploadFile file, Connection conn);

	/**
	 * @ 通过id来删除上传文件的信息， 不支持事务处理
	 * @param file
	 * @return 返回是否删除成功
	 */
	public boolean delete(long id);
	
	/**
	 * @  通过id来删除上传文件的信息， 支持事务处理
	 * @param file 
	 * @param conn
	 * @return 返回是否删除成功
	 */
	public boolean delete(long id, Connection conn);
	
	
	/**
	 * @ 更新上传文件的对象信息，不支持事务处理
	 * @param file	 
	 * @param id
	 * @return 返回受影响的行数
	 */
	public int update(UploadFile file,int id);
	
	
	/**
	 * @ 更新上传文件的对象信息，支持事务处理
	 * @param file	 
	 * @param id
	 * @param conn
	 * @return 返回受影响的行数
	 */
	public int update(UploadFile file,int id,Connection conn);

	/**
	 * @ 通过id来查找上传的文件信息，不支持事务处理
	 * @param file
	 * @return 返回查询到的对象信息
	 */
	public UploadFile query(int  id);
	

	/**
	 * @ 通过上传文件的在本地的真实名字进行查找（返回的是单个对象）
	 * @param realName
	 * @return 返回查询到的对象信息
	 */
	public UploadFile queryByRealName(String  realName);

	/**
	 * @ 通过上传文件时名字进行查找（返回的是多个对象）
	 * @param realName
	 * @return 返回查询到的对象信息
	 */
	public List<UploadFile> queryByOldName(String  oldName);
	
	
	/**
	 * @ 通过保存路径进行查找（返回的是多个对象）
	 * @param realName
	 * @return 返回查询到的对象信息
	 */
	public List<UploadFile> queryBySavePath(String  savePath);
	
	/**
	 * @ 拿到数据表内的全部信息
	 * @return
	 */
	public List<UploadFile> getAll();

	
	/**
	 * @ 统计指定id的文件是否存在
	 * @param id
	 * @return
	 */
	public boolean count(int id); 
	
	
	/**
	 * @ 通过上传文件时的名字统计数量
	 * @param oldName
	 * @return
	 */
	public long countByOldName(String oldName);
	
	/**
	 * @ 通过文件在本地保存的真实名字进行统计
	 * @param realName
	 * @return
	 */
	public long countByRealName(String realName);
	
	
	/**
	 * @ 统计上传在某一路径中的文件数量
	 * @param savePath
	 * @return
	 */
	public long countBySavePath(String savePath);
	
	/**
	 * @ 返回数据表的全部数据条数
	 * @return
	 */
	public  long count() ;

	
	/**
	 * @ 通过 HttpServletRequest对象来进行上传文件的数据保存
	 */
	public void saveUploadFile(HttpServletRequest req,BookUser user);
}
