package JDBCconnection;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;
	private static String excelFilePath;

	public static void openExcelFile(String path, String sheetName) {
		excelFilePath = path;
		try {

			FileInputStream ExcelFile = new FileInputStream(path);
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getCellData(int rowNum, int colNum) {
		try {
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			String cellData = cell.toString();
			return cellData;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void setCellData(String value, int rowNum, int colNum) {
		try {
			row = excelWSheet.getRow(rowNum);
			if (row == null) {
				row = excelWSheet.createRow(rowNum);
			}
			cell = row.getCell(colNum);

			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			FileOutputStream fileOut = new FileOutputStream(excelFilePath);
			excelWBook.write(fileOut);

			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getUsedRowsCount() {
		try {
			int rowCount = excelWSheet.getPhysicalNumberOfRows();
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;

		}
	}
}