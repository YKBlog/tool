package file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 
 * @Title: PropertiesUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>properties文件操作工具类
 * @Created on 2014年10月28日 下午5:57:23
 * @author yangkai
 */
public class PropertiesUtil {

    /**
     * 根据key读取value
     * 
     * @author yangkai
     * @param filePath
     * @param key
     * @return
     */
    public static String getPropertyByKey(String filePath, String key) {
        String value = "";
        Properties properties = loadPropertiesFile(filePath);
        try {
            if (null != properties)
                value = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            properties.clear();
        }
        return value;
    }

    /**
     * 读取properties的全部信息
     * 
     * @author yangkai
     * @param filePath
     */
    public static void getProperties(String filePath) {
        Properties properties = loadPropertiesFile(filePath);
        try {
            if (null != properties) {
                Enumeration en = properties.propertyNames();
                while (en.hasMoreElements()) {
                    String key = (String) en.nextElement();
                    String Property = properties.getProperty(key);
                    System.out.println(key + "="+Property);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            properties.clear();
        }
    }

    /**
     * 写入properties信息
     * 
     * @author yangkai
     * @param filePath
     * @param parameterName
     * @param parameterValue
     */
    public static boolean setProperty(String filePath, String key, String value) {
        boolean flag = false;
        Properties properties = loadPropertiesFile(filePath);
        try {
            if (null != properties) {
                properties.setProperty(key, value);
                properties.store(new FileOutputStream(filePath), "Update '" + key + "' value");
                String valueTemp = properties.getProperty(key);
                if (value.equals(valueTemp)) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Visit " + filePath + " for updating " + key + " value error");
        } finally {
            properties.clear();
        }
        return flag;
    }

    /**
     * 获取Properties对象
     * 
     * @author yangkai
     * @param filePath
     * @return
     */
    public static Properties loadPropertiesFile(String filePath) {
        Properties properties = null;
        try {
            InputStream in = getInputStream(filePath);
            if (null != in) {
                properties = new Properties();
                properties.load(in);
            }
        } catch (Exception e) {
            properties = null;
        }
        return properties;
    }

    public static InputStream getInputStream(String filePath) {
        try {
            // 方法一：
            URL url = ClassLoader.getSystemResource(filePath);
            return url == null ? null : url.openStream();
            // 方法二：
            // return
            // PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath)
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        // System.out.println(getPropertyByKey("test.properties", "name"));
        // System.out.println(setProperty("test.properties", "age", "1111"));
        getProperties("test.properties");

    }
}
