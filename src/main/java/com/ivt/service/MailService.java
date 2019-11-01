package com.ivt.service;

import com.ivt.entities.OrderEntity;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private ServletContext context;

    public boolean sendEmail(OrderEntity order) throws MessagingException, UnsupportedEncodingException, FileNotFoundException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setSubject("Hóa đơn điện tử của đơn hàng #" + order.getId());
        mimeMessageHelper.setTo(order.getCustomer().getEmail());
        mimeMessageHelper.setText("Order thành công", true);
        mailSender.send(mimeMessage);
        return true;
    }
}
