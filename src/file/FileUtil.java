package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import file.txt.TxtReadAndWrite;

/**
 * 
 * @Title: FileUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>文件处理工具
 * @Created on 2014-4-4 下午06:52:47
 * @author 杨凯
 */
public class FileUtil {

    private static int k = 1, m = 1; // 复制文件计数

    /**
     * 按五千行分开生成文件,并以,分割
     * 
     * @param inputStr
     * @param outputStr
     */
    public static void dealTxt(String inputStr, String outputStr) {

        List<String> list = TxtReadAndWrite.readTxt(inputStr);
        try {
            for (int i = 0; i < list.size(); i += 5000) {
                StringBuffer sb = new StringBuffer();
                List<String> listStr = null;
                if (i + 5000 < list.size()) {
                    listStr = list.subList(i, i + 5000);
                } else {
                    listStr = list.subList(i, list.size());
                }
                for (int j = 0; j < listStr.size(); j++) {
                    sb.append(listStr.get(j) + ",");
                }
                TxtReadAndWrite.createTxt(outputStr + (i + 5000) / 1000 + "千~" + (i + 10000) / 1000 + "千.txt", sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     * 
     * @param file
     * @throws IOException
     */
    public static void ergodicFolder(File file) {

        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) { // 遍历文件
            if (fileList[i].isFile()) { // 判断是文件
                
                try {
                    Thread.sleep(1l);// 休眠是必须的，否则会导致文件的覆盖
                    if ("summary.xml".equals(fileList[i].getName())) {
                        File summaryFile = new File("E:/yiyuan/summary/" + k + "/");
                        summaryFile.mkdir();
                        FileUtils.copyFile(fileList[i], new File(summaryFile.getAbsolutePath() + "/" + System.currentTimeMillis() + ".xml"));
                        m++;
                        if (m > 1000) {
                            k++;
                            m = 1;
                        }
                    } else if ("content.xml".equals(fileList[i].getName())) {
                        File contentFile = new File("E:/yiyuan/docinfo/" + k + "/" + System.currentTimeMillis());
                        contentFile.mkdir();
                        File desFile = new File(contentFile.getAbsolutePath() + "/docInfo.xml");
                        FileUtils.copyFile(fileList[i], desFile);
                        m++;
                        if (m > 1000) {
                            k++;
                            m = 1;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (fileList[i].isDirectory()) { // 判断是目录
                ergodicFolder(fileList[i]); // 递归
            }
        }
    }
    
/**
 * 遍历文件夹
 * @param file
 * @return
 */
    public static List<File> ergodicFile(File file) {
        List<File> list = new ArrayList<File>();
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) { // 判断是文件
            if (fileList[i].isFile()) {
                try {
                    Thread.sleep(1l);
                    list.add(fileList[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (fileList[i].isDirectory()) { // 判断是目录
                ergodicFile(fileList[i]); // 递归
            }
        }
        return list;
    }
}
