/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivt.model;

import com.ivt.entities.OrderDetailEntity;
import com.ivt.entities.OrderEntity;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 *
 * @author phand
 */
public class ExcelFileReportView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map map, Workbook workbook, HttpServletRequest hsr, HttpServletResponse response) throws Exception {
        List<OrderEntity> orders = (List<OrderEntity>) map.get("order");
        response.setHeader("Content-Disposition", "attachment; filename=forex-rates.xls");
        Sheet sheet = workbook.createSheet("List Orders");
        Sheet sheet2 = workbook.createSheet("Detail Order");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("#");
        header.createCell(1).setCellValue("Order number");
        header.createCell(2).setCellValue("Order date");
        header.createCell(3).setCellValue("Customer name");
        header.createCell(4).setCellValue("Customer phone");
        header.createCell(5).setCellValue("Note");
        header.createCell(6).setCellValue("Total price");
        header.createCell(7).setCellValue("Status");
//2
        Row header2 = sheet2.createRow(0);
        header2.createCell(0).setCellValue("#");
        header2.createCell(1).setCellValue("Order number");
        header2.createCell(2).setCellValue("Product Id");
        header2.createCell(3).setCellValue("Product name");
        header2.createCell(4).setCellValue("Size");
        header2.createCell(5).setCellValue("Color");
        header2.createCell(6).setCellValue("Uniprice");
        header2.createCell(7).setCellValue("Quantity");
        header2.createCell(8).setCellValue("Price");

        int i = 1;
        int stt = 1;

        int j = 1;
        int sttt = 1;
        double total = 0;
        for (OrderEntity order : orders) {
            Row rowcontain = sheet.createRow(i);
            rowcontain.createCell(0).setCellValue(stt);
            rowcontain.createCell(1).setCellValue(order.getId());
            rowcontain.createCell(2).setCellValue(order.getOrderDate().toString());
            rowcontain.createCell(3).setCellValue(order.getCustomer().getName());
            rowcontain.createCell(4).setCellValue(order.getCustomer().getPhoneNumber());
            rowcontain.createCell(5).setCellValue(order.getNote());
            rowcontain.createCell(6).setCellValue(order.getTotalPrice());
            rowcontain.createCell(7).setCellValue(order.getOrderStatus());

            for (OrderDetailEntity object : order.getListOrderDetail()) {
                Row rowcontain2 = sheet2.createRow(j);
                rowcontain2.createCell(0).setCellValue(sttt);
                rowcontain2.createCell(1).setCellValue(order.getId());
                rowcontain2.createCell(2).setCellValue(object.getProduct().getId());
                rowcontain2.createCell(3).setCellValue(object.getProduct().getName());
                rowcontain2.createCell(4).setCellValue(object.getSize());
                rowcontain2.createCell(5).setCellValue(object.getColor());
                rowcontain2.createCell(6).setCellValue(object.getUnitPrice());
                rowcontain2.createCell(7).setCellValue(object.getQuantity());
                rowcontain2.createCell(8).setCellValue(object.getPrice());
                j++;
                sttt++;
            }
            total+=order.getTotalPrice();
            i++;
            stt++;
        }
        Row rowcontain = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 5));
        rowcontain.createCell(0).setCellValue("Total Amount");
        rowcontain.createCell(6).setCellValue(total);
        //hsr.getSession().removeAttribute("order");
    }
    
}
