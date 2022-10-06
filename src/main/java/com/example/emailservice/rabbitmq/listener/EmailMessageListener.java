package com.example.emailservice.rabbitmq.listener;

import com.example.emailservice.email.config.EmailProperties;
import com.example.emailservice.email.model.BirthdayUser;
import com.example.emailservice.email.model.EmailMessage;
import com.example.emailservice.email.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Evelina Tubalets
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmailMessageListener {

    private final EmailService emailService;

    /**
     * Method which listens messages from RabbitMQ with email details
     * and sends congratulation emails for birthday users after getting email message.
     *
     * @param message which is getting from RabbitMQ.
     */
    @RabbitListener(queues = {"q.send-email-message"})
    public void getEmailMessage(final EmailMessage message) {
        log.info("I'm email-service and I got message - " + message + " from rabbit");

        final EmailProperties emailProperties = message.getEmailProperties();
        final List<BirthdayUser> users = message.getUsers();

        emailService.sendCongratulationEmail(emailProperties, users);
        log.info("congratulation emails sent to birthday users");
    }
}
