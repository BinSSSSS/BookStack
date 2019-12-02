package cn.tblack.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

public class BookUser {

	private long userId;       	//用户id
	private String account;		 //登录账号
	private String userName;  	//用户名	
	private String password;  	//密码
	private String phoneNum;	//手机号
	private String gender;    	//性别
	private Date brithday;   	//生日
	private String homeLand;	//祖国
	private long age;			//年龄
	private String personalSignature;	//个性签名
	private String job;			//工作
	private String email;		//邮箱
	
	
	private static final int NOT_NULL_CNT = 4;	//表中非空字段的数量
	private static final int MUTABLE_CNT = 10;	//经常修改的字段值
	
	public BookUser() {
		super();
	}
	
	public BookUser(BigDecimal userId, String userName, String accout, String password, String gender, Date birthday,
			String phoneNum, String homeLand, BigDecimal age, String personalSignature, String job, String email) {
		super();
		this.userId = userId.longValue();
		this.account = accout;
		this.userName = userName;
		this.password = password;
		this.phoneNum = phoneNum;
		this.gender = gender;
		this.brithday = birthday;
		this.homeLand = homeLand;
		this.age = age.longValue();
		this.personalSignature = personalSignature;
		this.job = job;
		this.email = email;
	}
	
	public BookUser(String account, String userName, String password,  String phoneNum) {
		this.account =  account;
		this.userName = userName;
		this.password = password;
		this.phoneNum = phoneNum;
	}
	


	public long getUserId() {
		return userId;
	}

	public void setUserId(BigDecimal userId) {
		
		
		this.userId = userId.longValue();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date birthday) {
		this.brithday = birthday;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getHomeLand() {
		return homeLand;
	}

	public void setHomeLand(String homeLand) {
		this.homeLand = homeLand;
	}
	
	public long getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
		this.age = age.longValue();
	}

	public String getPersonalSignature() {
		return personalSignature;
	}

	public void setPersonalSignature(String personalSignature) {
		this.personalSignature = personalSignature;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Object getAllNotId() {
		return null;
	}
	
	
	/**
	 * @ 拿到插入数据时必须要使用到的参数
	 * @return  返回从声明顺序必须要的数据（ 插入表数据时需要注意声明顺序）
	 */
	public Object[] getNecessaryParam() {
		
		
		Object[] params =  new Object[NOT_NULL_CNT];
		
		Field[] fields = this.getClass().getDeclaredFields();
		
		for(int i = 0; i < NOT_NULL_CNT;++i) {
			try {
				/*@ 通过反射技术来拿到类中的字段值- 按照声明顺序拿取*/
				params[i] = fields[i + 1].get(this);
			}catch(Exception e) {
				params[i] = null;
				e.printStackTrace();
			}
		}
		return params;
	}

	
	/**
	 * @ 返回一般情况下经常修改的表字段数据- 按照声明顺序返回
	 * @return 返回按照字段声明顺序获取的对象列表（id和account不可变）
	 */
	public Object[]  getMutableParam() {
		
		Object[] params =  new Object[MUTABLE_CNT];
		Field[] fields = this.getClass().getDeclaredFields();
		
		
		for(int i = fields.length -  MUTABLE_CNT;i < MUTABLE_CNT ;++i) {
			try {
				/*@ 通过反射技术来拿到类中的字段值- 按照声明顺序拿取*/
				params[i] = fields[i + 1].get(this);
			}catch(Exception e) {
				params[i] = null;
				e.printStackTrace();
			}
		}
		
		return params;
	}

	@Override
	public String toString() {
		return "BookUser [userId=" + userId + ", accout=" + account + ", userName=" + userName + ", password=" + password
				+ ", phoneNum=" + phoneNum + ", gender=" + gender + ", brithday=" + brithday + ", homeLand=" + homeLand
				+ ", age=" + age + ", personalSignature=" + personalSignature + ", job=" + job + ", email=" + email
				+ "]";
	}
	
	
}
