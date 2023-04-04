package com.trysol.excel;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.trysol.model.TrysolWebApp;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerator {
	private List<TrysolWebApp> apps;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	public ExcelGenerator(List<TrysolWebApp> apps) {
		this.apps = apps;
		workbook = new XSSFWorkbook();
	}
	private void writeHeader() {
		sheet = workbook.createSheet("Employee");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "id", style);
		createCell(row, 1, "name", style);
		createCell(row, 2, "email", style);
		createCell(row, 3, "subject", style);
		createCell(row, 4, "message", style);

	}
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((int) value);
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		}
		else if (value instanceof String) {
			cell.setCellValue((String) value);
		}else {
			cell.setCellValue((Double) value);
		}
		cell.setCellStyle(style);
	}
	
	private void write() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (TrysolWebApp record : apps) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, record.getId(), style);
			createCell(row, columnCount++, record.getName(), style);
			createCell(row, columnCount++, record.getEmail(), style);
			createCell(row, columnCount++, record.getSubject(), style);
			createCell(row, columnCount++, record.getMessage(), style);
		}
		
	}
	public void generate(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
