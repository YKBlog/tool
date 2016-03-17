package file.readExcel;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @Title: ReadExcel.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 * @Created on 2013-8-7 下午04:05:29
 * @author 杨凯
 */
public class StringReadExcel {

    public static String readExcel(File file) {
        StringBuffer sb = new StringBuffer();
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(file); // 构造Workbook（工作薄）对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wb == null) {
            return null;
        }
        Sheet[] sheet = wb.getSheets(); // 得到Sheet（工作表）对象
        if (sheet != null && sheet.length > 0) {
            // 对每个工作表进行循环
            for (int i = 0; i < sheet.length; i++) {   
                int rowNum = sheet[i].getRows();        // 得到当前工作表的行数
                for (int j = 0; j < rowNum; j++) {  
                    Cell[] cells = sheet[i].getRow(j);  // 得到当前行的所有单元格
                    if (cells != null && cells.length > 0) {
                        for (int k = 0; k < cells.length; k++) {           // 对每个单元格进行循环
                            String cellValue = cells[k].getContents();    // 读取当前单元格的值
                            sb.append(cellValue + "\t");
                        }
                    }
                    sb.append("\r\n");
                }
                sb.append("\r\n");
            }
        }
        wb.close();
        return sb.toString();
    }

}
