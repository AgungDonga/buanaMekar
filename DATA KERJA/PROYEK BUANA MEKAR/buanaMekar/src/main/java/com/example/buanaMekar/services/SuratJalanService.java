/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.repositories.OrderanRepository;
import com.example.buanaMekar.repositories.SuratJalanRepository;
import static com.lowagie.text.pdf.PdfName.DATA;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author Insane
 */
@Service
public class SuratJalanService {

    @Autowired
    SuratJalanRepository repo;

    @Autowired
    OrderanRepository orderanRepo;

    public String exportReport2(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Insane\\Desktop\\Report";

        List<SuratJalan> sj = repo.findAll();

        File file = ResourceUtils.getFile("classpath:sJalanReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(sj);
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

//    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//        String path = "D:\\INDEX";
//
//        File file = ResourceUtils.getFile("classpath:suratJalan.jrxml");
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("param1", "ALIONG COMPANY");
//        parameters.put("param2", "18/08/2020 23:04:55");
//        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
//
//        JasperDesign jd = JRXmlLoader.load(file.getAbsolutePath());
//        JasperReport jasperReport = JasperCompileManager.compileReport(jd);
//        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters);
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jp, path + "\\suratJalan.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jp, path + "\\suratJalan16.pdf");
//        }
//        return "success : " + path;
//        //kurang asem
//    }
    public List<SuratJalan> listAll() {
        return repo.findAll();
    }

    public List<SuratJalan> listAllSuratJalan() {
        return repo.findAllSuratJalan();
    }

    public List<SuratJalan> listDetailSuratJalan(String key1, String key2) {
        return repo.listDetailSuratJalan(key1, key2);
    }

    public void save(SuratJalan sj) {
        repo.save(sj);

    }

    public SuratJalan get(Integer integer) {
        return repo.findById(integer).get();

    }

    public void delete(Integer integer) {
        repo.deleteById(integer);

    }

//    public List<Orderan> getAllOrderan() {
//        return orderanRepo.findAll();
//    }
}
