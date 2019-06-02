package zq.shop.utils;

import java.util.UUID;

/**
 * UUID字符串生成工具
 * 功能：生成用户激活码
 * @author ZhouQi
 *
 */
public class UUIDUtils {

	//生成激活码
	public static String getUUID() {
		/**
		 * 去掉字符串中的-字符：uri地址传输时会将地址解析为ascii码传输，ascii码中没有-这个字符对应的编码，不处理的话，在地址访问后
		 * 携带的参数的值可能会解码错误，所以需要去掉	*******************************************************
		 */
		return UUID.randomUUID().toString().replace("-", "");
	}
}
