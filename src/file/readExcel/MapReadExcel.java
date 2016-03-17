package file.readExcel;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @Title: ReadExcel.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>使用map返回值，可以达到解析excel的时候去重复值
 * @Created on 2013-8-7 下午04:05:29
 * @author 杨凯
 * 
 */
public class MapReadExcel {

    public static Map<String, String> readExcel(File file) {
        Map<String, String> map = new HashMap<String, String>(); // 读取当前单元格的值;
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
                // sheet[i].getRows()得到当前工作表的行数
                for (int j = 1; j < sheet[i].getRows(); j++) {
                    Cell[] cells = sheet[i].getRow(j); // 得到当前行的所有单元格
                    if (cells != null && cells.length > 0) {
                        map.put(cells[1].getContents(), cells[2].getContents());
                    }
                }
            }
        }
        wb.close();
        return map;
    }
    
    public static void main(String[] args) {
        File file = new File("e:/互动百科合作医院.xls");
        Map<String, String> map = MapReadExcel.readExcel(file);
        StringBuilder sb = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> maps = (Entry<String, String>) it.next();
           String strSql = "insert into t_wiki_app_iframe(type_id,product_code,object_id,iframe_value,iframe_state)values(3,'doc','"+maps.getKey()+"','"+maps.getValue()+"',1);";
           //String strSql = "insert into t_object_apps(objid,productcode,appcode,blockcode,state,createdtime)values('" + maps.getKey() + "','doc','39yiyuan','39yiyuan',1,'2013-08-08 14:06:31');";
            sb.append(strSql + "\r\n");
        }

        File outFile = new File("e:/t_wiki_app_iframe.txt");
        //File outFile = new File("e:/t_object_apps.txt");
        try {
            RandomAccessFile raf = new RandomAccessFile(outFile, "rw");
            String str = new String(sb.toString().getBytes("utf-8"), "ISO-8859-1");
            raf.writeBytes(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
