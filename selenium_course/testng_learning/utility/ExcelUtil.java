package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class ExcelUtil {

	public static XSSFWorkbook book;
	public static Sheet sheet;
	private static final String DIR_LOCATION = System.getProperty("user.dir") + "\\resources\\testdata\\";
	private static String SHEET_PATH = "testdata.xlsx";
	private static final String SHEET_NAME = "Sheet1";
	static Logger logger = Logger.getLogger(ExcelUtil.class);
	static FileOutputStream outputStream = null;

	public static Object[][] readExcelData1() {
		Object[][] testData = null;
		try {
			if (!new File(DIR_LOCATION).exists())
				new File(DIR_LOCATION).mkdir();
			File file = new File(DIR_LOCATION + SHEET_PATH);
			if (file.exists()) {
				FileInputStream ip = new FileInputStream(file);
				book = new XSSFWorkbook(ip);
				sheet = book.getSheet(SHEET_NAME);
				int totalRows = sheet.getLastRowNum();
				int totalCols = sheet.getRow(0).getLastCellNum();
				testData = new Object[totalRows][totalCols];
				for (int i = 0; i < totalRows; i++) {
					for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
						testData[i][j] = sheet.getRow(i + 1).getCell(j).toString();
					}
				}
			} else
				file.createNewFile();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (EncryptedDocumentException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return testData;
	}

	public static Object[][] readExcelData() {
		Object [][]testData=null;
		Map<String, String> temp;
		try {
			if (!new File(DIR_LOCATION).exists())
				new File(DIR_LOCATION).mkdir();
			File file = new File(DIR_LOCATION + SHEET_PATH);
			if (file.exists()) {
				FileInputStream ip = new FileInputStream(file);
				book = new XSSFWorkbook(ip);
				sheet = book.getSheet(SHEET_NAME);
				int totalRows = sheet.getLastRowNum();
				testData = new Object[totalRows][1];
				for (int i = 0; i < totalRows; i++) {
					temp = new HashMap<>();
					for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
						temp.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
					}
					testData[i][0]=temp;
				}
			} else
				file.createNewFile();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (EncryptedDocumentException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return testData;
	}

	public static ArrayList<Object[]> readExcelData(String sheetName) {
		ArrayList<Object[]> testData = new ArrayList<>();
		Map<String, String> temp;
		try {
			if (!new File(DIR_LOCATION).exists())
				new File(DIR_LOCATION).mkdir();
			File file = new File(DIR_LOCATION + SHEET_PATH);
			if (file.exists()) {
				FileInputStream ip = new FileInputStream(file);
				book = new XSSFWorkbook(ip);
				int index = book.getSheetIndex(SHEET_NAME);
				sheet = book.getSheetAt(index);

				for (int i = 0; i < sheet.getLastRowNum(); i++) {
					temp = new HashMap<>();
					for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
						temp.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
					}
					testData.add(new Object[] { temp });
				}
			} else
				file.createNewFile();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (EncryptedDocumentException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		finally {
			try {
				book.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return testData;
	}

	public int getRowCount(String sheetName) {
		int index = book.getSheetIndex(SHEET_NAME);
		if (index == -1)
			return 0;
		else {
			sheet = book.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	public static List<String> readCSV(File csvname) throws Exception {
		List<String> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(csvname);) {
			while (scanner.hasNext()) {
				records.add(scanner.next());
			}
		}
		return records;
	}

	public static void readCSVFile(File csvname) {
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(csvname));
			String str[];
			while ((str = reader.readNext()) != null) {
				for (String temp : str) {
					System.out.print(temp);
				}
				System.out.println("");
			}
		} catch (IOException | CsvValidationException e) {
		} finally {
			reader = null;
		}
	}

	public void WriteIntoCSV() {
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter(new File("")));
			String[] headings = { "Name", "Location" };
			String[] data = { "Rohit", "Delhi" };
			String[] data1 = { "Sanjeev", "Delhi" };

			List<String[]> tempdata = Arrays.asList(headings, data, data1);
			writer.writeAll(tempdata);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer = null;
		}
	}

}
