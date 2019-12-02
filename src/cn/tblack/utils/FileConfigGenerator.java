package cn.tblack.utils;

import java.util.UUID;

/**
 * <span>用于文件的路径和文件名等的生成器</span>
 * @author TD唐登
 * @Date:2019年6月21日
 * @Version: 1.0(测试版)
 */
public class FileConfigGenerator {

	
	private static long fileId =  55555; //用于生成文件名的ID值（当上传的文件是HTML等， 那么则使用的是id值） 
	
	/**
	 * @ 创建HTML文件的文件名， 以ID值生成， 从55555开始进行产生
	 * @return
	 */
	public static String createHtmlName() {
		
		return Long.toUnsignedString(fileId++);
	}

	/**
	 * @ 返回以oldName生成的随机不重复的本地保存文件名(产生文件名格式为    UUID + _ + oldName)
	 * @param oldName
	 * @return
	 */
	public static String createRealName(String oldName) {
		
		return   UUID.randomUUID().toString() +  "_" + oldName;
	}

	/**
	 * @ 通过给定的文件名的HashCode来生成一个16进制的数字来表示该文件需要保存在哪个路径
	 * @param fileName
	 * @return
	 */
	public static String createSavePath(String fileName) {
		
		return Integer.toString((fileName.hashCode() >> 4) & 0xf);
	}
	
	
}
