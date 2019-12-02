package cn.tblack.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.jsp.JspWriter;

/**
 * <span>用于网页元素的打印</span>
 * @author TD唐登
 * @Date:2019年6月13日
 * @Version: 1.0(测试版)
 */
public class WebPrint {

	public static void println(PrintWriter out, Object o) {
		out.println("<p>" + o + "</p>");
	}
	
	public static void println(JspWriter out, Object o) {
		try {
			out.println("<p>" + o + "</p>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
