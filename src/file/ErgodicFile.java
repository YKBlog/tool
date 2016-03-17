package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * 遍历文件工具
 * 
 * @Title: ErgodicFile.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 * @Created on 2013-8-15 上午9:41:20
 * @author 杨凯
 */
public class ErgodicFile {

    private static int k = 1, m = 1;

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\xml数据");

        // ergodicFile(file, 0);
        ergodicFileFolder(file);
        // ergodicFolder(file);

    }

    /**
     * 生成文件
     * 
     * @param file
     * @param temp
     * @return
     * @throws IOException
     */
    public static List<File> ergodicFile(File file, int temp) throws IOException {
        List<File> list = new ArrayList<File>();
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {

            File docFile = new File("E:\\39yiyuan\\doc\\" + temp + ".xml");
            File summaryFile = new File("E:\\39yiyuan\\summary\\" + temp + ".xml");
            File contentFile = new File("E:\\39yiyuan\\content\\" + temp + ".xml");

            if (fileList[i].isFile()) { // 判断是文件
                if ("doc.xml".equals(fileList[i].getName())) {
                    FileUtils.copyFile(fileList[i], docFile); // copy文件
                } else if ("summary.xml".equals(fileList[i].getName())) {
                    FileUtils.copyFile(fileList[i], summaryFile);
                } else if ("content.xml".equals(fileList[i].getName())) {
                    FileUtils.copyFile(fileList[i], contentFile);
                }
            } else if (fileList[i].isDirectory()) { // 判断是目录
                ergodicFile(fileList[i], i); // 递归
            }
        }
        return list;
    }

    /**
     * 生成带文件夹的文件
     * 
     * @param file
     * @param temp
     * @return
     * @throws IOException
     */
    public static List<File> ergodicFileFolder(File file) throws IOException {
        List<File> list = new ArrayList<File>();
        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) { // 遍历文件

            if (fileList[i].isFile()) { // 判断是文件

                try {
                    Thread.sleep(1l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("doc.xml".equals(fileList[i].getName())) {
                    File docFile = new File("E:/yiyuan/doc/" + System.currentTimeMillis());
                    docFile.mkdir();
                    FileUtils.copyFile(fileList[i], new File(docFile.getAbsolutePath() + "/doc.xml"));
                } else if ("summary.xml".equals(fileList[i].getName())) {
                    File summaryFile = new File("E:/yiyuan/summary/" + System.currentTimeMillis());
                    summaryFile.mkdir();
                    FileUtils.copyFile(fileList[i], new File(summaryFile.getAbsolutePath() + "/summary .xml"));
                } else if ("content.xml".equals(fileList[i].getName())) {
                    File contentFile = new File("E:/yiyuan/docinfo/" + System.currentTimeMillis());
                    contentFile.mkdir();
                    FileUtils.copyFile(fileList[i], new File(contentFile.getAbsolutePath() + "/docInfo.xml"));
                }
            } else if (fileList[i].isDirectory()) { // 判断是目录
                ergodicFileFolder(fileList[i]); // 递归
            }
        }
        return list;
    }

    /**
     * 分数量遍历copy
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public static void ergodicFolder(File file) throws IOException {

        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) { // 遍历文件
            if (fileList[i].isFile()) { // 判断是文件
                // 这里是必须的，一定要休眠，否则会导致文件的覆盖
                try {
                    Thread.sleep(1l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("summary.xml".equals(fileList[i].getName())) {
                    File summaryFile = new File("E:/yiyuan/summary/" + k + "/");
                    summaryFile.mkdir();
                    FileUtils.copyFile(fileList[i], new File(summaryFile.getAbsolutePath() + "/" + System.currentTimeMillis() + ".xml"));
                    m++;
                    if (m > 1000) {
                        k++;
                        m = 1;
                    }
                } else

                if ("content.xml".equals(fileList[i].getName())) {
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
            } else if (fileList[i].isDirectory()) { // 判断是目录
                ergodicFolder(fileList[i]); // 递归
            }
        }
    }

    public static void file(File file) throws IOException {

        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) { // 遍历文件

            if (fileList[i].isFile()) { // 判断是文件
                if ("doc.xml".equals(fileList[i].getName())) {
                    fileList[i].delete();
                } else if ("summary.xml".equals(fileList[i].getName())) {
                    fileList[i].delete();
                } else if ("content.xml".equals(fileList[i].getName())) {
                    fileList[i].getName().replace("content.xml", "docInfo.xml");
                }
            } else if (fileList[i].isDirectory()) { // 判断是目录
                file(fileList[i]); // 递归
            }
        }
    }
}
