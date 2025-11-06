package com.sap.bits.api.LeaveScheduler.service;

import com.sap.bits.api.LeaveScheduler.model.LeaveApplication;
import com.sap.bits.api.LeaveScheduler.model.User;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Send email with leave application details to manager
     */
    @Async
    public void sendLeaveApplicationEmail(LeaveApplication leaveApplication) {
        logger.info("Sending email for leave application");
    }

    /**
     * Send email with leave approval details to employee
     */
    @Async
    public void sendLeaveApprovedEmail(LeaveApplication leaveApplication) {
        logger.info("Sending email for leave approval");
    }

    /**
     * Send email with leave rejection details to employee
     */
    @Async
    public void sendLeaveRejectedEmail(LeaveApplication leaveApplication) {
        logger.info("Sending email for leave rejection");
    }

    /**
     * Send email when leave is withdrawn
     */
    @Async
    public void sendLeaveWithdrawalEmail(LeaveApplication leaveApplication) {
        logger.info("Sending email for leave withdrawal");
    }

    /**
     * Send email notification about annual leave credit
     */
    @Async
    public void sendLeaveCreditEmail(User user) {
        logger.info("Sending email for leave credit");
    }

    /**
     * Send email notification about special leave credit
     */
    @Async
    public void sendSpecialLeaveCreditEmail(User user, LeaveType leaveType, float amount, String reason) {
        logger.info("Sending email for special leave credit");
    }

    @Async
    public void sendResetPasswordEmail(String email, String resetLink) {
        logger.info("Sending email for password reset");
    }

    /**
     * Process the HTML template using Thymeleaf
     */
    private String processTemplate(String templateName, Map<String, Object> variables) {
        Context context = new Context();
        variables.forEach(context::setVariable);
        return templateEngine.process(templateName, context);
    }

    /**
     * Send HTML email
     */
    @Retryable(value = MessagingException.class, maxAttempts = 3, backoff = @Backoff(delay = 20000))
    public void sendEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }

    @Recover
    public void recover(MessagingException e, String to, String subject, String htmlContent) {
        logger.error("Failed to send email to {} after multiple attempts: {}", to, e.getMessage());
    }
}
