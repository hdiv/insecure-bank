package org.hdivsamples.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class InsecureBankUtils {

	public static double round(double value, final int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	private static final String AUDIT_FILE_NAME = "bankAudit.log";

	private static final String AUDIT_LINE_TEMPLATE = "Date: %s; User: %s; Action: %s; Time taken(ms): %d";

	public static void audit(final Date date, final String user, final String action, final long timeTaken) {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {

			String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);

			File f = new File(AUDIT_FILE_NAME);

			if (f.exists()) {
				double bytes = f.length();
				if ((bytes / 1024 / 1024) >= 100) {
					f.renameTo(new File(AUDIT_FILE_NAME + "_" + strDate));
					f = new File(AUDIT_FILE_NAME);
				}
			}

			fileWriter = new FileWriter(f);
			printWriter = new PrintWriter(fileWriter);

			printWriter.printf(AUDIT_LINE_TEMPLATE, strDate, user, action, timeTaken);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (printWriter != null) {
				printWriter.close();
			}
			if (fileWriter != null) {
				try {
					fileWriter.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Workbook export(final String title, final String[] columns, final String[][] data) {
		// Create a Workbook
		Workbook workbook = new HSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		// Create a Sheet
		Sheet sheet = workbook.createSheet(title);

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Create Other rows and cells with employees data
		int rowNum = 1;
		for (String[] rowData : data) {
			Row row = sheet.createRow(rowNum++);

			for (int i = 0; i < rowData.length; i++) {
				String cellData = rowData[i];
				row.createCell(i).setCellValue(cellData);
			}

		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		return workbook;
	}
}
