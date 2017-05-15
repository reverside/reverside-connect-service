package za.co.reverside.service.command.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailAdapter {

	private String mailFrom = "Reverside<mail.zenerick@gmail.com>";

	@Autowired
	JavaMailSender mailSender;

	public void send(String to, String subject, String message, Map<String, String> attachments) throws Exception {
		System.out.println("Start Sending Email");
		MimeMessage mailMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
		helper.setFrom(mailFrom);
		helper.setTo(to);
		helper.setSubject(subject);
		for(final Map.Entry<String,String> entry : attachments.entrySet()){
			helper.addAttachment(entry.getKey(), new InputStreamSource() {
				@Override
				public InputStream getInputStream() throws IOException {
					return new URL(entry.getValue()).openConnection().getInputStream();
				}
			});
		}
		helper.setText(message, message.contains("</"));
		mailSender.send(mailMessage);
		System.out.println("Finished Sending Email");
	}
}
