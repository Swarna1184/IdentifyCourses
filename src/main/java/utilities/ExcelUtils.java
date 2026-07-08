package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    public static void writeLanguagesToExcel(List<String> languages) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Languages");
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
            FileOutputStream fileOut = new FileOutputStream("Languages.xlsx");
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
            FileOutputStream fileOut = new FileOutputStream("Levels.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Levels written to Excel");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void writeCourseDetailsToExcel(List<Map<String, String>> courses) {

        try {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Course Details");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Course Name");
            header.createCell(1).setCellValue("Learning Hours");
            header.createCell(2).setCellValue("Ratings");
            int rowNum = 1;
            for(Map<String, String> course : courses) {

                System.out.println("Writing -> " + course);

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(course.get("Name"));
                row.createCell(1).setCellValue(course.get("Hours"));
                row.createCell(2).setCellValue(course.get("Rating"));
            }
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }
            FileOutputStream fileOut =
                    new FileOutputStream("CourseDetails.xlsx");
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            workbook.close();
            System.out.println("Course Details written to Excel successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
