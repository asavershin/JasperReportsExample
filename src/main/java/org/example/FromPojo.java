package org.example;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class FromPojo {

  @SneakyThrows
  public static void main(String[] args) {
    List<User> users = new ArrayList<>();
    users.add(new User("Ivan Ivanov", 30, "ivan.ivanov@example.com"));
    users.add(new User("Maria Petrova", 25, "maria.petrova@example.com"));
    users.add(new User("Petr Sidorov", 40, "petr.sidorov@example.com"));

    JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(users);

    File reportFile = new File("template.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(reportFile.getAbsolutePath());

    Map<String, Object> parameters = new HashMap<>();

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

    JasperExportManager.exportReportToPdfFile(jasperPrint, "out.pdf");

    System.out.println("PDF отчет успешно создан.");
  }

}


