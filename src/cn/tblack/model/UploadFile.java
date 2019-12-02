package cn.tblack.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * <span>JavaBean 表示着上传文件的信息</span>
 * @author TD唐登
 * @Date:2019年6月21日
 * @Version: 1.0(测试版)
 */
public class UploadFile {

	private long fileId;         	//上传文件的ID
	private String oldName;  	//上传文件的原有名字
	private String savePath; 	//上传文件的本地保存路径
	private String realName;	//上传文件的真实名字
	private long fileSize; 		//上传文件的大小
	private String fileType;	//上传文件的类型
	private String fileDesc;	//上传文件的描述信息
	private String uploadUser;	//上传文件的用户姓名	
	private Timestamp uploadDate;	//文件上传的时间
	
	
	public UploadFile() {
		super();
	}


	public UploadFile(BigDecimal fileId, String oldName, String savePath, String realName, long fileSize, String fileType,
			String fileDesc, String uploadUser, Timestamp uploadDate) {
		super();
		this.fileId = fileId.longValue();
		this.oldName = oldName;
		this.savePath = savePath;
		this.realName = realName;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.fileDesc = fileDesc;
		this.uploadUser = uploadUser;
		this.uploadDate = uploadDate;
	}

	public long getFileId() {
		return fileId;
	}


	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId.longValue();
	}


	public String getOldName() {
		return oldName;
	}


	public void setOldName(String oldName) {
		this.oldName = oldName;
	}


	public String getSavePath() {
		return savePath;
	}


	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public long getFileSize() {
		return fileSize;
	}


	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getFileDesc() {
		return fileDesc;
	}


	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}


	public String getUploadUser() {
		return uploadUser;
	}


	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}


	public Timestamp getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}


	/**
	 * @ 拿到该类定义的成员变量， 不包括id
	 * @return
	 */
	public Object[] getDeclaredFieldNotId() {
		
		/*@拿到所有的字段值*/
		Field[] fields = this.getClass().getDeclaredFields();
		
		/*@将字段值作为Object类型进行保存，并且忽略id字段*/
		Object[] objs = new Object[fields.length - 1];  
		
	
		/*@将字段值的内容读取出来并保存在对象数组中*/
		for(int i = 0; i < fields.length - 1; ++i) {
			try {
				objs[i] = fields[i + 1].get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return objs;
	}


	@Override
	public String toString() {
		return "UploadFile [fileId=" + fileId + ", oldName=" + oldName + ", savePath=" + savePath + ", realName=" + realName
				+ ", fileSize=" + fileSize + ", fileType=" + fileType + ", fileDesc=" + fileDesc + ", uploadUser="
				+ uploadUser + ", uploadDate=" + uploadDate + "]";
	}
	
	
}
