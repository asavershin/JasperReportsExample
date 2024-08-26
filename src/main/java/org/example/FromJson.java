package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.json.data.JsonDataSource;

public class FromJson {

  @SneakyThrows
  public static void main(String[] args) {
    String templatePath = "template.jrxml";
    String jsonDataPath = "NotListIn.json";
    String outputPdfPath = "out.pdf";

    JasperReport jasperReport = JasperCompileManager.compileReport(templatePath);

    File jsonFile = new File(jsonDataPath);
    FileInputStream jsonStream = new FileInputStream(jsonFile);
    JsonDataSource jsonDataSource = new JsonDataSource(jsonStream);

    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), jsonDataSource);
    JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdfPath);

    System.out.println("PDF успешно создан: " + outputPdfPath);
  }

}
