package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelUtils {

    public static void writeLanguagesToExcel(List<String> languages) {

        try {

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Languages");

            // Header Row
            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("Language");
            header.createCell(1).setCellValue("Language Count");

            int rowNum = 1;

            for(String data : languages) {

                String language = data.replaceAll("\\(.*\\)", "").trim();

                String count = data.replaceAll(".*\\(", "")
                        .replace(")", "")
                        .trim();

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(language);
                row.createCell(1).setCellValue(count);
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            FileOutputStream fileOut =
                    new FileOutputStream("Languages.xlsx");

            workbook.write(fileOut);

            fileOut.close();
            workbook.close();

            System.out.println("Languages written to Excel");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeLevelsToExcel(List<String> levels) {

        try {

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Levels");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("Level");
            header.createCell(1).setCellValue("Level Count");

            int rowNum = 1;

            for(String data : levels) {

                String level = data.replaceAll("\\(.*\\)", "").trim();

                String count = data.replaceAll(".*\\(", "")
                        .replace(")", "")
                        .trim();

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(level);
                row.createCell(1).setCellValue(count);
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            FileOutputStream fileOut =
                    new FileOutputStream("Levels.xlsx");

            workbook.write(fileOut);

            fileOut.close();
            workbook.close();

            System.out.println("Levels written to Excel");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
