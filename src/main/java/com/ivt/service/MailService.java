package com.ivt.service;

import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

//        String list = "<tr><td>Kính gửi anh/chị " + order.getCustomer().getName() + "</td></tr>"
//                + "<tr style=\"background-color: grey\"><td>Tên Sản Phẩm</td><td>Số lượng</td><td>Đơn giá</td><td>Tông tạm</td></tr>";
//        for (OrderDetailEntity orderDetail : order.getListOrderDetail()) {
//            list += "<tr><td>" + orderDetail.getProduct().getName() + "</td><td>"
//                    + orderDetail.getQuantity() + "</td><td>"
//                    + orderDetail.getUnitPrice() + "</td><td>"
//                    + orderDetail.getPrice() + "</td><td></tr>";
//        }
//        list += "<hr><tr><td>Tổng thanh toán: " + order.getTotalPrice() + "</td></tr>";
//        StringBuilder html = new StringBuilder();
//        String line;
//        File file = new File(context.getRealPath("/WEB-INF/pages/mail-page.jsp"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                new FileInputStream(file), "UTF8"));
//        int row = 1;
//        while ((line = br.readLine()) != null) {
//            html.append(line);
//            if (row == 15) {
//                html.append(list);
//            }
//            row++;
//        }
//        br.close();
//        list = html.toString();
//        mimeMessageHelper.setText(list, true);
        mimeMessageHelper.setText("Chúc mừng bạn đã order thành công", true);
        mailSender.send(mimeMessage);
        return true;
    }
}
