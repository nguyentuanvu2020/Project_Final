/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.model;

import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author phand
 */
public class PdfReportView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map model, Document document,
            PdfWriter writer, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getInstance(localeVN);
        ServletContext context = hsr.getServletContext();
        String urlImage = context.getRealPath("/image");
        int index = urlImage.indexOf("target");
        String pathFolder = urlImage.substring(0, index) + "src\\main\\webapp\\resources\\font\\";
        OrderEntity order = (OrderEntity) model.get("Data");
        Paragraph tenshop = new Paragraph("VH Shoes");
        Paragraph diachishop = new Paragraph("01 Nguyen Van Linh, Hai Chau, TP Da Nang \n Contact: 0900000000 - 0900000001");
        Chapter chapter1 = new Chapter(tenshop, 1);
        Font fontbt = new Font(BaseFont.createFont(pathFolder + "vuArial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        chapter1.setNumberDepth(0);
        Paragraph title1 = new Paragraph("ORDER DETAIL");
        title1.setAlignment(Element.ALIGN_CENTER);
        Paragraph sohoadon = new Paragraph("Order number:   " + String.valueOf(order.getId()));
        Paragraph khachhang = new Paragraph("Customer name:   " + order.getCustomer().getName(), fontbt);
        Paragraph ngaydathang = new Paragraph("Order date:   " + order.getOrderDate());
//        Paragraph hinhthucthanhtoan = new Paragraph("Hình thức thanh toán:   " + "update");
//        Paragraph diachogiao = new Paragraph("Địa chỉ giao hàng");
        Paragraph diachi = new Paragraph("Address:   " + order.getCustomer().getAddress(),fontbt);
        Paragraph tien = new Paragraph("Total amout:   " + format.format(order.getTotalPrice()));
        tien.setAlignment(Element.ALIGN_LEFT);
        //diachi.setIndentationLeft(80);
        PdfPTable t = new PdfPTable(7);
        t.setSpacingBefore(25);
        t.setSpacingAfter(25);
        PdfPCell c1 = new PdfPCell(new Phrase("#"));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("Product name"));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("Size"));
        t.addCell(c3);
        PdfPCell c4 = new PdfPCell(new Phrase("Color"));
        t.addCell(c4);
        PdfPCell c5 = new PdfPCell(new Phrase("Quantity"));
        t.addCell(c5);
        PdfPCell c6 = new PdfPCell(new Phrase("Unitprice"));
        t.addCell(c6);
//            PdfPCell c = new PdfPCell(new Phrase("Khuyến mãi"));
//            t.addCell(c);
        PdfPCell c7 = new PdfPCell(new Phrase("Price"));
        t.addCell(c7);
        int i = 1;
        double tong = 0;
        for (OrderDetailEntity element : order.getListOrderDetail()) {
//                tong = tong + element.getDongia() * element.getSoluong() - ((element.getDongia() * Khuyenmai.layTiengiam(element.getMakhuyenmai())) / 100) * element.getSoluong();
            t.addCell(i + "");
            t.addCell(new Phrase(element.getProduct().getName()));
            t.addCell(new Phrase(String.valueOf(element.getSize())));
            t.addCell(new Phrase(element.getColor()));
            t.addCell(new Phrase(String.valueOf(element.getQuantity())));
            t.addCell(new Phrase(String.valueOf(format.format(element.getProduct().getPrice()))));
//                t.addCell(new Phrase(Khuyenmai.layTenKM(element.getMakhuyenmai()), fontbt));
            t.addCell(new Phrase(String.valueOf(format.format(element.getPrice()))));
            i++;
        }
        //Paragraph tenkhach = new Paragraph("Phan Đình Hiệp", font);
        //document.add(new Paragraph("Viblo Asia"));
        document.add(chapter1);
        document.add(diachishop);
        document.add(title1);
        document.add(sohoadon);
        document.add(khachhang);
        document.add(ngaydathang);
//            document.add(hinhthucthanhtoan);
//            document.add(diachogiao);
        document.add(diachi);
        document.add(t);
        document.add(tien);
    }

}
