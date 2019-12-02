package cn.tblack.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tblack.model.BookUser;
import cn.tblack.service.UploadFileService;

public class UploadUtils {

	
	/**
	 * @ 更加通用的写入操作， 传递请求和响应对象，以及一个上传用户的信息， 将请求对象内的数据传递给上传文件服务对象进行处理
	 * @ 将处理的结果写入到响应对象中
	 * @param req
	 * @param resp
	 * @param user
	 * @param ufService
	 * @return
	 */
	public static boolean upload(HttpServletRequest req, HttpServletResponse resp,
			BookUser user,UploadFileService ufService) {
		
		try {
			ufService.saveUploadFile(req, user);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				resp.getOutputStream().write(e.getMessage().getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		try {
			resp.getOutputStream().write("Success".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		return true;
	}
}
