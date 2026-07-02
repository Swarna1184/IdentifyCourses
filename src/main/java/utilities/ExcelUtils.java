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
            int rowNum = 0;
            for (String lang : languages) {
                Row row = sheet.createRow(rowNum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(lang);
            }
            FileOutputStream fileOut = new FileOutputStream("Languages.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Languages written to Excel ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
