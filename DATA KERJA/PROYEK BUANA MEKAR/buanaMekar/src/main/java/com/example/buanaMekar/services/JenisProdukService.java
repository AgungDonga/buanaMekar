/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.JenisProduk;
import com.example.buanaMekar.repositories.JenisProdukRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author Insane
 */
@Service
public class JenisProdukService {
    
    @Autowired
    JenisProdukRepository repo;
    //yaya
    public String exportReport2(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Insane\\Desktop\\Report";

        List<JenisProduk> jenisProduk = repo.findAll();

        File file = ResourceUtils.getFile("classpath:jenisProduk.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jenisProduk);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created", "Developer");
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jp, path + "\\suratJalan.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jp, path + "\\suratJalan.pdf");
        }
        return "success : " + path;
    }

    public List<JenisProduk> listAll() {
        return repo.findAll();

    }

    public void save(JenisProduk jenisProduk) {
        repo.save(jenisProduk);

    }

    public JenisProduk get(Integer id) {
        return repo.findById(id).get();

    }

    public void delete(Integer id) {
        repo.deleteById(id);

    }
    
    public List<JenisProduk> getAll(){
        return repo.findAll();
    }
}
