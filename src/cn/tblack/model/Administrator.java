package cn.tblack.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <span>后台管理员</span>
 * @author TD唐登
 * @Date:2019年6月17日
 * @Version: 1.0(测试版)
 */
public class Administrator {
	
	private long adminId ;		//管理员id
	private String adminName;   //管理员名称 (与BookUser中的用户名对应， 是外键的关系)
	private String authorizer; //授权人
	private Timestamp authorizerTime; //授权时间
	
	public Administrator() {
		super();
	}

	public Administrator(long adminId, String adminName, String authorizer, Timestamp authorizerTime) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.authorizer = authorizer;
		this.authorizerTime = authorizerTime;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	public void setAdminId(BigDecimal adminId) {
		this.adminId = adminId.longValue();
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAuthorizer() {
		return authorizer;
	}

	public void setAuthorizer(String authorizer) {
		this.authorizer = authorizer;
	}

	public Timestamp getAuthorizerTime() {
		return authorizerTime;
	}

	public void setAuthorizerTime(Timestamp authorizerTime) {
		this.authorizerTime = authorizerTime;
	}
	
}
