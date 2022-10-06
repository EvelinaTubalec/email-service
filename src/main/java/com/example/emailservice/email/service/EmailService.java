package com.example.emailservice.email.service;

import com.example.emailservice.email.config.EmailProperties;
import com.example.emailservice.email.model.BirthdayUser;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

/**
 * @author Evelina Tubalets
 */
@Service
@AllArgsConstructor
public class EmailService {

    private final EmailProperties properties;

    public void sendCongratulationEmail(final EmailProperties properties, final List<BirthdayUser> users) {
        users.forEach(it -> createCongratulationMessageAndSend(it, properties));
    }

    private void createCongratulationMessageAndSend(final BirthdayUser user, final EmailProperties properties) {
        final SimpleMailMessage message = createCongratulationMessage(user);
        getMailSender(properties).send(message);
    }

    private SimpleMailMessage createCongratulationMessage(final BirthdayUser user) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Congratulation Message");
        message.setText(format("%s %s, Happy Birthday!", user.getFirstName(), user.getLastName()));
        message.setFrom(properties.getEmailFrom());
        return message;
    }

    private JavaMailSender getMailSender(final EmailProperties properties) {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getHost());
        mailSender.setPort(properties.getPort());
        mailSender.setUsername(properties.getUsername());
        mailSender.setPassword(properties.getPassword());
        return mailSender;
    }
}
