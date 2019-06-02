package zq.shop.alipay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092500595445";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChcRL28NOmlwelnMYpT8bz2IbiC8FMj/KrkXrQsLE0iOgEvxBfIC5iWxcFZuBxlvSrljeoPXGZDnMSG99BlQiwqMp6Hk7YuGfP7Pq0318pXcMbMDsu08wGn3RLnXtDire8u0+yzJLEJY8OoWS4gyTgft8JHlThdA6+CFFfJSrSrNHf27GIZKp5pbEd1JRqkLIC11iY6x4CLd2wCnmlSY3QWK7bPllNUYGk+NV4XOZYiyt4yOd3g5fpMqz6JzL+GmWEpOefXofmuQVowTQeEcqRanrX7MpG21GXQbTWSezrWrjRXR7+gUp8lUgH2EDFnmwy/zLQOUJ5ihu61ZOeNc27AgMBAAECggEAfOrXd8gEnQSD4DucgV6/XPMP+1yJJ0rCxMp+s3fkxfehP/rvGgv2mBhPidh+lqXtj89eJYnVmk657yst5rsAzGDuuus0qzCU8yg1HTsxkPqB+0mooaU0W3OKMvAZ7rspTJnLI1hzv94oAXQUUUj4gdTpk06q8wAwYA5jmDFUH0pq2ghNiMDQrf5JucbrJQ8MfhBVkDYThl7ytV1K7dnnEloOBTJica/+pJXB+HnTKxome/wW2viJfqweRD8dMl63cLsHjQ1yIx/+8ey0UCB6dBOBVwhyM0mFV7NquSud3M5Y1ttVdJhl0a5FxnImVGlvOwgo1cSrbNyoo6tGuPhu4QKBgQDWI2ugu/uRBq5y5iYVB14IO8eD8FIztXWTIHeIQUmNojxKpRvNKvQ5wnjROe97DB7ycOdJ2qAAZgNQr61uCgmVF1xhZmbCMaUz5BVNoTlvns00osMXUjkNUSfV43jtS88Q0WT1V6kzYjpfkzJVkhKv4BggkbJvv0GRU9dWxlDBKQKBgQDBAHFcNHHGeI3vxscjieCKG+O4bPftA2+pJ5GRfLExHgBH0tKNuqjh92sSyCV+GAEmbp8nyjnYUQwO1n0dOIBFlLK6tFLMJ/yJBl1MAUx+bKmgGGDkHg/NRNCguFMt4kNTZ30+qcXaiGsBOqnfam6rVDAe/I7LIJT8FrSFLKFAQwKBgCDyFCYHpf27HG3bEPLdTk9P+zNGby87dNps/Vb+THW5BzOo0W0wdaDJVdNC6aBxzfUY/8yyETneeBYa4UDz1GOW04S8v4STJMqmBc0KyA5vTT0S6ZcV9GcjHFDhDYLczf/VZ4YDtamNLFP2DqFxtuROQwgHwn9E2q6kSpY0Lsp5AoGBAL66bkxdc3ZcWn/zI4amUaSKTS9LWHL8K1Oxy7RH0l+ks0hHOlZfeVfdhw3Hhu2mUTSmq1fKDAIOjej35CqFVJXujfXeD9i7R2AuTvK2cd6aCcMxdEbawmjrMXUy0tiMOlZo9id/5QvCNmS/kXI9HuLnTqrAkWiBaVn6IildYnk7AoGAC0mWU6ORnmRO/XeWncxECYwxoEqhH0dvxunWL7YzwsUDelLhlddjjUfWfqpcVobd8Ms5Iv8gmpxduXPcrqjPm6ztsNtkmyrHzZ5wECUqANzJq7rPiUbnWtmEedSX1f2yMljct1BKZTrLJBZCc/fGbDxxUuaI6P0EOHpDRWiJPNc=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA40D+FA7klRutLShfUnxqoveHd6FjVU9lMk4wa9C7g0+5cxSWTAgD/ZRrhkPnTbO3ZKH3dMJa3X++feLWEchkThytYFQOQaCz1tkixEMRA5BzDcHzIsRFcE8XCWzXqZkkLElUhYSh335JMv7Tfujp+Bb2eeWD63nvw9J9I5YyKxo0KKIfqOO/tMDbiPOBAh6EzoX/mJ+7qkW21eBLFbTAOMwKmD/R1mGZKZL2iKohzp5Ifot5hDS6yRGn+ud2SMfYRNG+0tMf15G6AyxXdJ4ui5o9TEkzFpwb2nFEyt95RQpobMjLPyOm+CwbhvubMXWRDzlIzEc2hpS5JGmUMbeOMQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/BookStore/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String return_url = "http://127.0.0.1:8080/BookStore/return_url.jsp";
	public static String return_url = "http://127.0.0.1:8080/BookStore/returnUrl.action";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

