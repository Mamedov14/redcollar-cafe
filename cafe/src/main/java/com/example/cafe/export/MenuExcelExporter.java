package com.example.cafe.export;

import com.example.cafe.entity.Menu;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class MenuExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Menu>  menuList;

    public MenuExcelExporter(List<Menu> menuList) {
        this.menuList = menuList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Menu");
    }

    private void writeHeaderRow() {
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("Меню ID");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Блюдо");
        cell.setCellStyle(style);


        cell = row.createCell(2);
        cell.setCellValue("Цена");
        cell.setCellStyle(style);


        cell = row.createCell(3);
        cell.setCellValue("Дата");
        cell.setCellStyle(style);

    }

    private void writeDataRows() {
        int rowCount = 1;

        for (Menu menu : menuList) {
            if (menu.getDate().equals(LocalDate.now().toString())) {
                Row row = sheet.createRow(rowCount++);

                Cell cell = row.createCell(0);
                cell.setCellValue(menu.getId());

                cell = row.createCell(1);
                cell.setCellValue(menu.getDish().getName());

                cell = row.createCell(2);
                cell.setCellValue(menu.getPrice());

                cell = row.createCell(3);
                cell.setCellValue(menu.getDate());
            }
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
