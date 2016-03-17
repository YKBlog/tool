package file.parseXml;

import java.util.List;

/**
 * 
 * @Title: XmlDocument.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>定义XML文档建立与解析的接口
 * @Created on 2013-8-9 上午10:10:21
 * @author 杨凯
 */
public interface XmlDocument {
    
    /*
     * 建立XML文档 
     * @param fileName 文件全路径名称
     */
    public void createXml(String fileName);

    /*
     * 解析XML文档 
     *  @param fileName 文件全路径名称
     */
    public List<String> parserXml(String fileName);
}