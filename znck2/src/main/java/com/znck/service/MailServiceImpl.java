package com.znck.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 邮箱激活service
 * 
 * @author 肖舒翔 2019-02-27
 * @version 1.0
 */
@Service
@Component
public class MailServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	private JavaMailSender mailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.mailSender = javaMailSender;
	}
	
	@Async("testExecutorPool")
	public void sendHtmlMailByThread(String from, String to, String title, String text) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			// 发送人
			helper.setTo(to);
			// 接收人
			helper.setSubject(title);
			// 标题
			helper.setText(text, true);
			// 内容
			mailSender.send(message);
		} catch (Exception e) {
			logger.error("发送邮件失败");
		}
		logger.info("发送邮件完毕");
	}
}
