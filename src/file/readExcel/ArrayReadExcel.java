package file.readExcel;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
 
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
 
/**
 * 
 * @Title: ExcelReader.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>
 * @Created on 2013-8-8 上午11:22:11
 * @author 杨凯
 */
public class ArrayReadExcel {

    public static ArrayList<String[]> readExcel(File excelFile) throws BiffException, IOException {
        ArrayList<String[]> list = new ArrayList<String[]>();
        Workbook rwb = null;
        Cell cell = null;
        InputStream stream = new FileInputStream(excelFile);
        rwb = Workbook.getWorkbook(stream);   // 获取Excel文件对象
        Sheet sheet = rwb.getSheet(0);     // 获取文件的指定工作表 默认的第一个
        for (int i = 1; i < sheet.getRows(); i++) {  // 行数(表头的目录不需要，从1开始)
            String[] str = new String[sheet.getColumns()];  // 创建一个数组 用来存储每一列的值
            for (int j = 0; j < sheet.getColumns(); j++) {  // 列数
                cell = sheet.getCell(j, i);
                str[j] = cell.getContents(); //当前i行、当前j列对应的单元格内容，赋值给当前一维数组的当前j数据元素
            }
            list.add(str);
        }
        return list;
    }
     
    public static void main(String[] args) {
        String excelFileName = "E:/互动月总结.xls";
        try {
            ArrayList<String[]> list = ArrayReadExcel.readExcel(new File(excelFileName));
            for (int i = 0; i < list.size(); i++) { 
                String[] str = (String[])list.get(i);   //读取Excel中的当前第i行数据； list中的每一行就是一个一维数组；
                for (int j = 0; j < str.length; j++) {
                    System.out.print(str[j]);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}