package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static void saveCarWashDataToExcel(List<List<String>> data) throws IOException {
		String path = System.getProperty("user.dir") + "//carwash.xlsx";
		FileOutputStream file = new FileOutputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet s = wb.createSheet("carwash_data");
		
		XSSFRow heading = s.createRow(0);
		heading.createCell(0).setCellValue("Name");
		heading.createCell(1).setCellValue("Rating");
		heading.createCell(2).setCellValue("Reviews");
		heading.createCell(3).setCellValue("Phone Numbers");
		heading.createCell(4).setCellValue("Address");
		
		for (int i=0; i<data.size(); i++) {
			List<String> temp = data.get(i);
			
			if (Float.parseFloat(temp.get(1)) > 4.0 && Integer.parseInt(temp.get(2)) > 20) {
				XSSFRow row = s.createRow(i+1);
				for (int j=0; j<temp.size(); j++) {
					row.createCell(j).setCellValue(temp.get(j));
				}
			}
		}
		
		wb.write(file);
		wb.close();
		file.close();
	}
	
	public static void saveGymSubMenuDataToExcel(List<String> data) throws IOException {
		String path = System.getProperty("user.dir") + "//carwash.xlsx";
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet s = wb.createSheet("gym_sub_menus");
		
		XSSFRow heading = s.createRow(0);
		heading.createCell(0).setCellValue("Sub Menus");
		
		for (int r=0; r<data.size(); r++) {
			XSSFRow row = s.createRow(r+1);
			row.createCell(0).setCellValue(data.get(r));
		}
		
		FileOutputStream f = new FileOutputStream(path);
		wb.write(f);
		wb.close();
		file.close();
	}
}
