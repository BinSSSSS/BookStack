package cn.tblack.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <span>该类保存着uploadfile.properties配置的全部信息， 当该类被加载的时候进行初始化</span>
 * <span>该类为单例模式， 只有一个对象可以被使用</span>
 * @author TD唐登
 * @Date:2019年6月21日
 * @Version: 1.0(测试版)
 */
public class UploadPropertiesMap extends PropertiesMap{

	private static final long serialVersionUID = 1L;

	private UploadPropertiesMap(Properties properties) {
		super(properties);
	}
	private static UploadPropertiesMap instance;
	
	
	/*@当类被加载的时候初始化， 通过properties文件进行构造。*/
	static {
		
		/*@使用文件缓冲加载*/
//		InputStream in =  null;
//		try {
//			in = new BufferedInputStream(new FileInputStream("src/uploadfile.properties"));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		/*@使用类加载器*/
		InputStream in = UploadPropertiesMap.class.getClassLoader().getResourceAsStream("uploadfile.properties");
		System.out.println(in);
		Properties properties  = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			System.out.println(e);
		}
		instance =  new UploadPropertiesMap(properties);
	}


	public static UploadPropertiesMap getInstance() {
		return instance;
	}
	

}
