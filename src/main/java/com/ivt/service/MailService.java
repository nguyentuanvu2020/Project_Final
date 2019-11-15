package com.ivt.service;

import com.ivt.entities.AccountEntity;
import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

    public boolean sendNewOrderMailPage(OrderEntity order) throws MessagingException, UnsupportedEncodingException, FileNotFoundException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
//        ServletContext context = request.getServletContext();
//        String urlImage = context.getRealPath("/image");
//        int index = urlImage.indexOf("target");
//        String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\image\\";
//        String duongdan = pathFolder + "/Balenciaga Trackbalenciaga-track-31573486804109.0-orange-replica-2.jpg";
//        File file2 = new File(duongdan);
//        if (file.exists()) {
//            thongbao = "duong dan dung va co file";
//        } else {
//            thongbao = "duong dan sai va eo co file";
//        }
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setSubject("Hóa đơn điện tử của đơn hàng #" + order.getId());
        mimeMessageHelper.setTo("cat03031994@gmail.com");
        String listItem = "";
//        String list = "<tr><td>Kính gửi anh/chị " + order.getCustomer().getName() + "</td></tr>"
//                + "<tr style=\"background-color: grey\"><td>Tên Sản Phẩm</td><td>Số lượng</td><td>Đơn giá</td><td>Tông tạm</td></tr>";
        for (OrderDetailEntity orderDetail : order.getListOrderDetail()) {
            listItem += "<tr><td><img src=\"item1\" alt=\"\">Ảnh</td>"
                    + "<td style=\"font-size:110%;color: #ed1d1d;border-bottom: 1px solid gray;\">" + orderDetail.getProduct().getName()
                    + "<br><span  style=\"color: grey;font-size: 90%;\">" + orderDetail.getColor() + "/" + orderDetail.getSize()
                    + "</span></td><td style=\"border-bottom: 1px solid gray;\">" + vn.format(orderDetail.getUnitPrice()) + "đ"
                    + "</td><td style=\"border-bottom: 1px solid gray;\">" + orderDetail.getQuantity()
                    + "</td><td style=\"border-bottom: 1px solid gray;\">" + vn.format(orderDetail.getPrice()) + "đ"
                    + "</td></tr>";
        }
//        list += "<hr><tr><td>Tổng thanh toán: " + order.getTotalPrice() + "</td></tr>";
        StringBuilder html = new StringBuilder();
        String line;
        File file = new File(context.getRealPath("/WEB-INF/jsp/newOrder-mail-page.html"));
//        File file2 = new File("D:\\New folder\\Project_Final\\src\\main\\webapp\\resources\\image\\logo\\tvshop.jpg");
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF8"));
        int row = 1;

        while ((line = br.readLine()) != null) {
            html.append(line);
            if (row == 15) {
                html.append(new Date());
            }
            if (row == 28) {
                html.append(order.getCustomer().getName());
            }
            if (row == 31) {
                html.append("#00000" + order.getId());
            }
            if (row == 34) {
                html.append(vn.format(order.getTotalPrice() + 40000) + "đ");
            }
            if (row == 39) {
                html.append(order.getCustomer().getPhoneNumber());
            }
            if (row == 46) {
                html.append(order.getCustomer().getAddress());
            }
            if (row == 53) {
                html.append(order.getCustomer().getEmail());
            }
            if (row == 56) {
                html.append(order.getOrderDate());
            }
            if (row == 71) {
                html.append(listItem);
            }
            if (row == 79) {
                html.append(vn.format(order.getTotalPrice()) + "đ");
            }
            if (row == 97) {
                html.append(vn.format(order.getTotalPrice() + 40000) + "đ");
            }
            row++;
        }
        br.close();
//        list = html.toString();
//        mimeMessageHelper.addInline("leftSideImage",
//                new File(duongdan));
        mimeMessageHelper.setText(html.toString(), true);
        mailSender.send(mimeMessage);
        return true;
    }

    public boolean sendEmailRegister(OrderEntity order, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException, FileNotFoundException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        ServletContext context = request.getServletContext();
        String urlImage = context.getRealPath("/image");
        int index = urlImage.indexOf("target");
        String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\image\\";
        String duongdan = pathFolder + "/" + "Balenciaga Trackbalenciaga-track-31573486804109.0-orange-replica-2.jpg";
        File file2 = new File(duongdan);
//        if (file.exists()) {
//            thongbao = "duong dan dung va co file";
//        } else {
//            thongbao = "duong dan sai va eo co file";
//        }
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setSubject("Hóa đơn điện tử của đơn hàng #" + order.getId());
        mimeMessageHelper.setTo("cat03031994@gmail.com");
        mimeMessageHelper.setText("OK", true);
        mimeMessageHelper.addInline("leftSideImage",
                new File(duongdan));
        mailSender.send(mimeMessage);
        return true;
    }

    public boolean sendNewAccountMailPage(AccountEntity newAccount) throws MessagingException, UnsupportedEncodingException, FileNotFoundException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setSubject("Thông báo tài khoản");
        mimeMessageHelper.setTo("cat03031994@gmail.com");
        StringBuilder html = new StringBuilder();
        String line;
        File file = new File(context.getRealPath("/WEB-INF/jsp/register-mail-page.html"));
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF8"));
        int row = 1;

        while ((line = br.readLine()) != null) {
            html.append(line);
            if (row == 22) {
                html.append(newAccount.getName());
            }
            row++;
        }
        br.close();
        mimeMessageHelper.setText(html.toString(), true);
        mailSender.send(mimeMessage);
        return true;
    }
    
    public boolean sendCancelMailPage(OrderEntity order) throws MessagingException, UnsupportedEncodingException, FileNotFoundException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setSubject("Thông báo đơn hàng");
        mimeMessageHelper.setTo("cat03031994@gmail.com");
        StringBuilder html = new StringBuilder();
        String line;
        File file = new File(context.getRealPath("/WEB-INF/jsp/cancel-order-mail-page.html"));
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF8"));
        int row = 1;

        while ((line = br.readLine()) != null) {
            html.append(line);
            if (row == 22) {
                html.append(order.getCustomer().getName());
            }
            row++;
        }
        br.close();
        mimeMessageHelper.setText(html.toString(), true);
        mailSender.send(mimeMessage);
        return true;
    }
    
    public boolean sendCodeMailPage(String email,int code) throws MessagingException, UnsupportedEncodingException, FileNotFoundException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setSubject("Thông báo tài khoản");
        mimeMessageHelper.setTo("cat03031994@gmail.com");
        StringBuilder html = new StringBuilder();
        String line;
        File file = new File(context.getRealPath("/WEB-INF/jsp/code-password-mail-page.html"));
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF8"));
        int row = 1;

        while ((line = br.readLine()) != null) {
            html.append(line);
            if (row == 22) {
                html.append(email);
            }
            if (row == 26) {
                html.append(code);
            }
            row++;
        }
        br.close();
        mimeMessageHelper.setText(html.toString(), true);
        mailSender.send(mimeMessage);
        return true;
    }
}
