package zq.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件的工具类
 * @author ZhouQi
 */
public class MailUtils {

	public static void sendMail(String to,String code) throws AddressException, MessagingException {
		Properties props = new Properties();
		props.setProperty("mail.smtp", "localhost");
		//Session对象，与邮箱服务器的连接
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@Edshop.com", "123");
			}
			
		});
		//构建邮件信息
		Message msg = new MimeMessage(session);
		//发件人
		msg.setFrom(new InternetAddress("service@Edshop"));
		//收件人
		msg.setRecipient(RecipientType.TO, new InternetAddress(to));
		//设置标题
		msg.setSubject("来自“教育图书商城”的账户激活邮件");
		//设置正文
		msg.setContent("<h2>来自“教育图书商城”官网的激活邮件</h2><br/><span>点此链接立即激活账户</span><br/><h3><a href='http://127.0.0.1:8080/BookStore/user_active.action?code="+code+"'>http://127.0.0.1:8080/BookStore/user_active.action?code="+code+"</a></h3>","text/html;charset=UTF-8");
		//发送对象
		Transport.send(msg);
	}
}
