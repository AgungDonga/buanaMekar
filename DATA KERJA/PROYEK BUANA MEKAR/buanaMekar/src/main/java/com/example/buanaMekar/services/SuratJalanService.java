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

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path  = "C:\\Users\\Insane\\Desktop\\Report";
        List<SuratJalan> suratJalans = repo.findAll();

        File file = ResourceUtils.getFile("classpath:suratJalan.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(suratJalans);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy", "Developer");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\suratJalan.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\suratJalan.pdf");
        }
        return "success : " + path;
        //kurang asem
    }

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
