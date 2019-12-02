package cn.tblack.utils;

import java.util.Random;
import cn.tblack.service.FactoryService;
import cn.tblack.service.UserService;

/**
 * <span>账号生成器-用于产生随机的10位账户用于前端页面注册使用-需要保证产生的数据不重复</span>
 * @author TD唐登
 * @Date:2019年6月16日
 * @Version: 1.0(测试版)
 */
public class AccountGenerator implements  Generator<Long>{

	
	private static Random rand ;
	
	private static final long BOUND =  10000000000L; //最大产生10位的数字
	
//	private static final long BOUND = Long.valueOf("10000000000");  //最大产生10位的数字
	private static UserService uService;
	
	static{
		rand = new Random();
		uService =  FactoryService.getUserService();
	}
	
	
	
	@Override
	public  Long next() {
		
		/*@ 随机产生10位的整数，并查询是否在账户， 如果不存在的话， 则可以使用*/
		long account =  (rand.nextLong() + BOUND / 10) % BOUND;
		
		/*@ 确保产生的随机数不小于8位数,并且数据库内不存在该账号*/
		while(account < (BOUND / 100) || uService.countByAccount(account) > 0) {
			account =  (rand.nextLong() + BOUND / 10) % BOUND;
		}
	
			
		return Math.abs(account);
	}
	
	
}
