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
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author Insane
 */
@Service
public class InvoiceService {
    
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
    public String exportReport2(String reportFormat) throws FileNotFoundException, JRException {
        try{
            String url1 = "jdbc:mysql://localhost/buana_mekar?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "";
// 
        conn = DriverManager.getConnection(url1, user, password);
        }catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        //end
        
        String path = "D:\\INDEX2";
        File file = ResourceUtils.getFile("classpath:reportInvoice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param1", "ALIONG COMPANY");
        parameters.put("param2", "18/08/2020 23:04:55");
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, conn);
            
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jp, path + "\\suratJalan.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jp, path + "\\InvoiceCUYYY.pdf");
        }
        return "success : " + path;
    }
    
}
