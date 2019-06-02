package zq.shop.utils;

import java.io.Console;
import java.io.UnsupportedEncodingException;

public class MessyCode {

	@SuppressWarnings("finally")
	public static String getUtf8(String value) {
		String str = null;
		try {
			str = new String(value.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			//可以打印日志
			
		} finally {
			return str;
		}
	}
}
