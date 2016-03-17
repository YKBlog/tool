package file.readExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @Title: ReadExcel.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>返回list的格式有解析excel可能出现重复值的情况
 * @Created on 2013-8-7 下午04:05:29
 * @author 杨凯
 */
public class ListReadExcel {

    public static List<Map<Integer,String>> readExcel(File file) {
        List<Map<Integer,String>>  list = new ArrayList<Map<Integer,String>>();

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
            Map<Integer,String> map = null;
            for (int i = 0; i < sheet.length; i++) {   
                // sheet[i].getRows()得到当前工作表的行数
                for (int j = 1; j < sheet[i].getRows(); j++) {  
                    Cell[] cells = sheet[i].getRow(j);  // 得到当前行的所有单元格
                    if (cells != null && cells.length > 0) {
                        map = new HashMap<Integer,String>();      // 读取当前单元格的值
                        for (int k = 0; k < cells.length; k++) {      // 对每个单元格进行循环
                            String cellValue = cells[k].getContents(); 
                            map.put(k,cellValue);
                        }
                    }
                    list.add(map);
                }
            }
        }
        wb.close();
        return list;
    }
    
    /**
     * 解析excel2007；即.xlsx文件
     * 
     * @param path
     * @return
     */
    public static List<Map<Integer, String>> readXlsx(String path) {
        List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();
        Map map = new HashMap<Integer, String>();
        StringBuffer sb = new StringBuffer();
        try {
            POIFSFileSystem fs = null;
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径 已过时，废弃使用
            // XSSFWorkbook xwb = new XSSFWorkbook(path);
            // excle的类型
            String readType = path.substring(path.lastIndexOf("."));
            File file = new File(path);
            if (file.exists()) {
                fs = new POIFSFileSystem( new FileInputStream(file));
            } else {
                System.out.println("文件不存在！");
            }
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0); // 读取第一章表格内容
            for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) { // 循环输出表格中的内容
                for (int j = sheet.getRow(i).getFirstCellNum(); j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容
                    sb.append(sheet.getRow(i).getCell(j).toString() + "\r\n");
                }
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
public static void main(String [] args){
    File file = new File("e:/互动百科合作医院.xls");
    List<Map<Integer, String>> lists = ListReadExcel.readExcel(file);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < lists.size(); i++) {
        Map<Integer, String> maps = lists.get(i);
        //String strSql = "insert into t_wiki_app_iframe(type_id,product_code,object_id,iframe_value,iframe_state)values(3,'doc','"+maps.get(1)+"','"+maps.get(2)+"',1);";
        String strSql = "insert into t_object_apps(objid,productcode,appcode,blockcode,state,createdtime)values('"+maps.get(1)+"','doc','39yiyuan','39yiyuan',1,'2013-08-08 14:06:31');";
        sb.append(strSql + "\r\n");
        
    }
    File outFile = new File("e:/t_object_apps.txt");
    try {
        RandomAccessFile raf = new RandomAccessFile(outFile, "rw");
        String str = new String(sb.toString().getBytes("utf-8"),"ISO-8859-1");
        raf.writeBytes(str);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
