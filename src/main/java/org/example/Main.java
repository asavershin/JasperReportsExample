package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;

public class Main {

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Usage: java -jar JasperJsonToPdf.jar <jrxml/jasper file name> <json file name>");
      System.exit(1);
    }

    String currentDir = System.getProperty("user.dir");

    String jrxmlOrJasperFileName = args[0];
    String jsonFileName = args[1];

    String jrxmlOrJasperPath = currentDir + File.separator + jrxmlOrJasperFileName;
    String jsonPath = currentDir + File.separator + jsonFileName;

    try {
      String outputPath = currentDir + File.separator + "out.pdf";

      InputStream jsonStream = new FileInputStream(jsonPath);
      JsonDataSource jsonDataSource = new JsonDataSource(jsonStream);

      JasperPrint jasperPrint;

      if (jrxmlOrJasperPath.endsWith(".jrxml")) {
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlOrJasperPath);
        jasperPrint = JasperFillManager.fillReport(jasperReport, null, jsonDataSource);
      } else if (jrxmlOrJasperPath.endsWith(".jasper")) {
        jasperPrint = JasperFillManager.fillReport(jrxmlOrJasperPath, null, jsonDataSource);
      } else {
        throw new IllegalArgumentException("Invalid file type. Provide .jrxml or .jasper file.");
      }

      JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);

      System.out.println("PDF file has been generated and saved at: " + outputPath);

    } catch (JRException e) {
      System.err.println("JasperReports error: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

}
