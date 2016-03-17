package file.readExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import file.FileUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @Title: ExcelParseCreateUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>excel解析和生成工具
 * @Created on 2014-4-4 下午06:52:29
 * @author 杨凯
 */
public class ExcelParseCreateUtil {

    /**
     * 生成excel
     * 
     * @param fileDir
     * @param existDocInfoList
     */
    public void saveToXls(String fileDir, List<Map<String, Object>> existDocInfoList) {
        try {
            File file = new File(fileDir);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
            // 标题的单元格样式
            WritableCellFormat titleWritableCellFormat = new WritableCellFormat();
            titleWritableCellFormat.setBackground(Colour.YELLOW);
            WritableSheet writableSheet = writableWorkbook.createSheet("doc", 0);
            // 生成标题，并设置标题的单元格状态
            writableSheet.addCell(new Label(0, 0, "DOC_HIS_ID", titleWritableCellFormat));
            writableSheet.addCell(new Label(1, 0, "DOC_TITLE", titleWritableCellFormat));
            writableSheet.addCell(new Label(2, 0, "DOC_ID", titleWritableCellFormat));
            writableSheet.addCell(new Label(3, 0, "DOC_HIS_EDITOR_USER_NICK", titleWritableCellFormat));
            writableSheet.setColumnView(0, 100);
            writableSheet.setColumnView(1, 100);
            writableSheet.setColumnView(2, 100);
            writableSheet.setColumnView(3, 80);
            Map<String, Object> obj = new HashMap<String, Object>();
            int rowindex = 0;
            for (int i = 0; i < existDocInfoList.size(); i++) {
                obj = existDocInfoList.get(i);
                if (obj == null) {
                    continue;
                }
                rowindex = i + 1;
                writableSheet.addCell(new Label(0, rowindex, (String) obj.get("")));
                writableSheet.addCell(new Label(1, rowindex, (String) obj.get("")));
                writableSheet.addCell(new Label(2, rowindex, (String) obj.get("")));
                writableSheet.addCell(new Label(3, rowindex, (String) obj.get("")));
            }
            writableWorkbook.write();
            writableWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析excel，解析一个excel文件
     * 
     * @param path
     * @return
     */
    public static List<String> readOneExcel(String path) {
        List<String> list = new ArrayList<String>();
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(new File(path)); // 构造Workbook（工作薄）对象
            if (wb == null) {
                return null;
            }
            Sheet sheet = wb.getSheet(3); // 得到Sheet（工作表）对象
            for (int j = 1; j < sheet.getRows(); j++) { // sheet[i].getRows()得到当前工作表的行数
                Cell[] cells = sheet.getRow(j); // 得到当前行的所有单元格
                if (cells != null && cells.length > 0) {
                    list.add(cells[0].getContents());
                }
            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析excel，解析多个excel了，读取整个文件夹下的所有excel
     * 
     * @param path
     * @return
     */
    public static List<String> readManyExcel(String path) {
        List<File> listFile = FileUtil.ergodicFile(new File(path));
        List<String> list = new ArrayList<String>();
        Workbook wb = null;
        try {
            for (int i = 0; i < listFile.size(); i++) {
                wb = Workbook.getWorkbook(listFile.get(i)); // 构造Workbook（工作薄）对象
                if (wb == null) {
                    return null;
                }
                Sheet sheet = wb.getSheet(3); // 得到Sheet（工作表）对象
                for (int j = 1; j < sheet.getRows(); j++) { // sheet[i].getRows()得到当前工作表的行数
                    Cell[] cells = sheet.getRow(j); // 得到当前行的所有单元格
                    if (cells != null && cells.length > 0) {
                        list.add(cells[0].getContents());
                    }
                }
                wb.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
