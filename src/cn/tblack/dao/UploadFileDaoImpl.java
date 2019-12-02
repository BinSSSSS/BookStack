package cn.tblack.dao;

import java.sql.Connection;
import java.util.List;

import cn.tblack.model.UploadFile;
import cn.tblack.utils.NumberFormat;
import cn.tblack.utils.Objects;

/**
 * <span>UploadDao接口的具体实现， 该类表示着操作上传文件数据的具体方法实现</span>
 * @author TD唐登
 * @Date:2019年6月21日
 * @Version: 1.0(测试版)
 */
public class UploadFileDaoImpl extends BaseDao<UploadFile> implements UploadFileDao{

	/*@只有包访问权限*/
	protected UploadFileDaoImpl() {
		
	}
	
	
	@Override
	public boolean insert(UploadFile file) {
	
		return this.insert(file,null);
	}

	@Override
	public boolean insert(UploadFile file, Connection conn) {
	
		String sql =  "INSERT INTO upload_file(old_name,save_path,real_name,file_size,file_type,file_desc,upload_user,upload_date)"
				+ "VALUES(?,?,?,?,?,?,?,?)";
		
		
		/*@ 非事务处理*/
		if(conn == null) {
			return super.update(sql,file.getDeclaredFieldNotId()) > 0;
		}
		
		/*@事务处理*/
		return super.update(conn, sql,file.getDeclaredFieldNotId()) > 0;
		
	}

	@Override
	public boolean delete(long id) {
		return this.delete(id,null);
	}

	@Override
	public boolean delete(long id, Connection conn) {
		
		String sql =  "DELETE upload_file WHERE FILE_ID=?";
	
		
		/*@非事务处理*/
		if(conn == null) {
			return super.update(sql, id) > 0;
		}
		
		/*@事务处理*/
		return super.update(conn,sql, id) > 0;
	}

	@Override
	public int update(UploadFile file, int id) {
		return this.update(file, id, null);
	}

	@Override
	public int update(UploadFile file, int id, Connection conn) {

		String sql = "UPDATE upload_file SET OLD_NAME=?,SAVE_PATH=?,REAL_NAME=?,FILE_SIZE=?,FILE_TYPE=?,FILE_DESC=?, UPLOAD_USER=?" + 
				"WHERE FILE_ID=?";

		Object[] org = file.getDeclaredFieldNotId();		//原来的属性集合
		Object[] attributes = Objects.referenceOf(org, org.length - 1); //去除上传日期属性
		
		
		/*@ 非事务处理*/
		if(conn == null) {
			return super.update(sql, attributes);
		}
		
		/*@事务处理*/
		return super.update(conn, sql, attributes);
	}

	@Override
	public UploadFile query(int id) {
		
		String sql = "SELECT file_id fileId, old_name oldName, save_path savePath, real_name realName," + 
				"file_size fileSize, file_type fileType, file_desc fileDesc,upload_user uploadUser, upload_date uploadDate " + 
				"FROM upload_file WHERE file_id=? ";
		
		return super.query(sql,id);
	}

	@Override
	public UploadFile queryByRealName(String realName) {
		String sql = "SELECT file_id fileId, old_name oldName, save_path savePath, real_name realName," + 
				"file_size fileSize, file_type fileType, file_desc fileDesc,upload_user uploadUser, upload_date uploadDate " + 
				"FROM upload_file WHERE real_name=?";
		return super.query(sql, realName);
	}

	@Override
	public List<UploadFile> queryByOldName(String oldName) {
		String sql = "SELECT file_id fileId, old_name oldName, save_path savePath, real_name realName," + 
				"file_size fileSize, file_type fileType, file_desc fileDesc,upload_user uploadUser, upload_date uploadDate " + 
				"FROM upload_file WHERE old_name=?";
		return super.getList(sql, oldName);
	}

	@Override
	public List<UploadFile> queryBySavePath(String savePath) {
		String sql = "SELECT file_id fileId, old_name oldName, save_path savePath, real_name realName," + 
				"file_size fileSize, file_type fileType, file_desc fileDesc,upload_user uploadUser, upload_date uploadDate " + 
				"FROM upload_file WHERE save_path=?";
		return super.getList(sql, savePath);
	}

	@Override
	public List<UploadFile> getAll() {
		
		String sql = "SELECT file_id fileId, old_name oldName, save_path savePath, real_name realName," + 
				"file_size fileSize, file_type fileType, file_desc fileDesc,upload_user uploadUser, upload_date uploadDate " + 
				"FROM upload_file";
		
		return super.getList(sql);
	}

	@Override
	public boolean count(int id) {
		String sql = "SELECT COUNT(FILE_ID) FROM upload_file WHERE FILE_ID=?";
		
		return NumberFormat.getNum(super.getValue(sql, id)) > 0;
	}

	@Override
	public long countByOldName(String oldName) {
		String sql = "SELECT COUNT(FILE_ID) FROM upload_file WHERE OLD_NAME=?";
		
		return NumberFormat.getNum(super.getValue(sql, oldName));
	}

	@Override
	public long countByRealName(String realName) {
		String sql = "SELECT COUNT(FILE_ID) FROM upload_file WHERE REAL_NAME=?";
		
		return NumberFormat.getNum(super.getValue(sql, realName));
	}

	@Override
	public long countBySavePath(String savePath) {
		String sql = "SELECT COUNT(FILE_ID) FROM upload_file WHERE SAVE_PATH=?";
		
		return NumberFormat.getNum(super.getValue(sql, savePath));
	}


	@Override
	public long count() {
		String sql = "SELECT COUNT(FILE_ID) FROM upload_file";
		
		return NumberFormat.getNum(super.getValue(sql));
	}

}
