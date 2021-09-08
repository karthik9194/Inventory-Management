package com.karthik.inventory.taskscheduler;

import com.karthik.inventory.repository.OrderRepository;
import com.karthik.inventory.repository.ProductRepository;
import com.karthik.inventory.model.entity.Product;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.karthik.inventory.appconstant.InventoryConstants.ScheduledCronTab.EVERY_DAY_5_00;

@Component
@Service
public class SalesReport {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private ProductRepository productRepository;

    @Scheduled(fixedRate = 10000)
    public void hourlySalesReport() throws FileNotFoundException {
        //exportReport();
        System.out.println("Hello hourly");
    }

    @Scheduled(cron = EVERY_DAY_5_00)
    public void dailySalesReport(){
        System.out.println("Hello Daily");
    }

//    public void exportReport() throws JRException, FileNotFoundException {
//
//        String path = "c:\\user";
//
//        List<Product> product = productRepository.findAll();
//
//        //Load file and compile it
//        File file = ResourceUtils.getFile("classpath:Hourly_Sales_Report.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport((file.getAbsolutePath()));
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(product);
//        Map<String, Object> mapParm = new HashMap<>();
//        mapParm.put("createdBy", "Sales Dept");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapParm, dataSource);
//        JasperExportManager.exportReportToPdfFile(jasperPrint,path + "\\sales_report.pdf");
//    }
}
