package com.redberyl.notification.service;

import com.redberyl.notification.entity.MailConfig;
import com.redberyl.notification.repository.MailConfigRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Service
public class NotificationService {

    @Autowired
    private MailConfigRepository mailConfigRepo;

    public boolean sendEmail(String to, String subject, String body) {
        MailConfig config = mailConfigRepo.findTopByOrderByIdDesc();

        if (config == null) {
            log.error("No mail config found in DB");
            return false;
        }

        try {

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(config.getHost());
            mailSender.setPort(config.getPort());
            mailSender.setUsername(config.getUsername());
            mailSender.setPassword(config.getPassword());
            mailSender.setProtocol(config.getProtocol());

            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.debug", "true");

            if (config.getProperties() != null && !config.getProperties().isEmpty()) {
                Map<String, Object> jsonProps = new HashMap<>();

                String json = config.getProperties()
                        .replace("{", "")
                        .replace("}", "");

                String[] entries = json.split(",");
                for (String entry : entries) {
                    String[] kv = entry.split(":");
                    if (kv.length == 2) {
                        jsonProps.put(kv[0].trim().replace("\"", ""), kv[1].trim().replace("\"", ""));
                    }
                }

                jsonProps.forEach(props::put);
            }

            mailSender.setJavaMailProperties(props);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);


            mailSender.send(message);

            return true;

        } catch (MessagingException | RuntimeException e) {
            log.error("Failed to send email: {}", e.getMessage());
            return false;
        }
    }
}
