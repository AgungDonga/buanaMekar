/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.repositories.InvoiceRepository;
import com.example.buanaMekar.repositories.OrderanRepository;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author Insane
 */
@Service
public class InvoiceService {

    HttpServletResponse response = null;
    @Autowired
    InvoiceRepository repo;

    @Autowired
    OrderanRepository OrderanRepo;

    public List<Invoice> listAll() {
        return repo.findAll();

    }

    public List<Invoice> listAllInvoice() {
        return repo.getAllInvoice();

    }
    
    public List<Invoice> findBulanan(String key1){
        return repo.findBulanan(key1);
    }
    
    public String totalBulanan(String key1){
        return repo.totalBulanan(key1);
    }

    public void save(Invoice invoice) {
        repo.save(invoice);

    }

    public Invoice get(String string) {
        return repo.findById(string).get();

    }

    public void delete(String string) {
        repo.deleteById(string);

    }

    public List<Orderan> getAllOrderan() {
        return OrderanRepo.findAll();
    }

    Connection conn;

    public String exportReport2(String reportFormat, String id, String id2) throws FileNotFoundException, JRException, IOException {

        try {
            String url1 = "jdbc:mysql://localhost/buana_mekar?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "";
// 
            conn = DriverManager.getConnection(url1, user, password);
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        //end

        String path = "D:\\INDEX2";
        File file = ResourceUtils.getFile("classpath:reportInvoice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param1", id);
        parameters.put("param2", id2);
        System.out.println("id=" + id);
        System.out.println("id2=" + id2);
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, conn);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment" + "; filename=Invoice.pdf");
//        response.setContentType("application/x/download");
//        response.setHeader("Content-Disposition", String.format("attachment; filename=\"Invoice.pdf\""));
//        OutputStream outputStream = response.getOutputStream();
        if (reportFormat.equalsIgnoreCase("pdf")) {
            ByteArrayOutputStream finalReport = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, finalReport);
            PrintWriter ouputStream = response.getWriter();
            ouputStream.write(new String(finalReport.toByteArray()));
            ouputStream.flush();
//            FacesContext.getCurrentInstance().getExternalContext().responseReset();
//            JasperExportManager.exportReportToPdfStream(jp, outputStream);
            JasperExportManager.exportReportToPdfFile(jp, path + "\\Invoice.pdf");
        }
        return "succes";
    }

}
