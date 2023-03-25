package qtriptest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class DP {
	
	
	
	public static DataFormatter formatter = new DataFormatter();
   

	@DataProvider(name="qtripDataProvider")
	public Object[][] loginData(Method m) throws IOException {
			String filePath = System.getProperty("user.dir") + "//src//test//resources//DatasetsforQTrip.xlsx";
			//System.out.println("inside data Provider");

			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(m.getName());
	
			System.out.println(sh.getPhysicalNumberOfRows());
			System.out.println(sh.getRow(0).getPhysicalNumberOfCells());
			int RowNum = sh.getPhysicalNumberOfRows();
			int ColNum = sh.getRow(0).getPhysicalNumberOfCells();
	
			String[][] xlData = new String[RowNum-1][ColNum-1];
	
			for (int i = 0; i < RowNum-1; i++) 
			{
				XSSFRow row = sh.getRow(i + 1);
				for (int j = 1; j < ColNum; j++) 
				{
					if (row == null)
						xlData[i][j-1] = "";
					else {
						XSSFCell cell = row.getCell(j);                 
						if (cell == null)
							xlData[i][j-1] = ""; 
						else {
							String value = formatter.formatCellValue(cell);
							xlData[i][j-1] = value.trim();  
							//System.out.println(xlData[i][j-1]);                          
						}
					}
				}
			}  
			 
			return xlData;
		}   



}
