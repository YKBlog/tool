package file.parseXml;

import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * 
 * @Title: SaxParseXml.java
 * @Description: <br>
 *               <br>sax 解析xml
 * @Created on 2016年3月12日 下午4:11:29
 * @author yangkai
 * @version 1.0
 */
public class SaxParseXml {

    public void createXml(String fileName) {
        System.out.println("<<" + fileName + ">>");
    }

    public String parserXml(String fileName) {
        SAXParserFactory saxfac = SAXParserFactory.newInstance();
        MySAXHandler handler = new MySAXHandler();
        try {
            SAXParser saxparser = saxfac.newSAXParser();
            InputStream is = new FileInputStream(fileName);
            saxparser.parse(is,handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  handler.getSb();
    }
}

class MySAXHandler extends DefaultHandler {

    private String tagName = null;// 当前解析的元素标签
    private StringBuffer sb = null;

    public String getSb() {
        System.out.println("getSb--------"+sb.toString());
        return sb.toString();
    }

    public void startDocument() throws SAXException {
        sb = new StringBuffer();
        System.out.println("文档开始打印了");
    }

    public void endDocument() throws SAXException {
        System.out.println("文档打印结束了");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.tagName = localName;
        System.out.println("startElement----"+localName);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.tagName = null;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (tagName != null) {
            System.out.println("characters----"+tagName);
            String data = new String(ch, start, length);
            if ("name".equals(tagName)) {
                sb.append("<h2>医院简介</h2><p>" + data + "位于");
            }
            if ("address".equals(tagName)) {
                sb.append(data + "成立于");
            }
            if ("year".equals(tagName)) {
                sb.append(data + "；");
            }
            if ("level".equals(tagName)) {
                sb.append("是一所" + data);
            }
            if ("type".equals(tagName)) {
                sb.append(data + "</p>");
            }
            if ("summarize".equals(tagName)) {
                sb.append("<p>" + data + "</p>");
            }
            if ("achievement".equals(tagName)) {
                sb.append("<p>" + data + "</p>");
            }
            if ("honour".equals(tagName)) {
                sb.append("<p>" + data + "</p>");
            }
            if ("equipment".equals(tagName)) {
                sb.append("<p>" + data + "</p>");
            }
            /*if ("Depts".equals(tagName)) {
                sb.append("<p>医院科室有：");
                if ("name".equals(tagName)) {
                    sb.append(data + "(");
                }
                if ("labIntro".equals(tagName)) {
                    sb.append(data + ")、");
                }
            }
            if ("Doctors".equals(data)) {
                sb.append("</p><h2>主治医生</h2><p>");
                if ("Doctorname".equals(tagName)) {
                    sb.append(data + "：所在科室");
                }
                if ("Doctordept".equals(tagName)) {
                    sb.append(data + "专治");
                }
                if ("disease".equals(tagName)) {
                    sb.append(data + "。</p>");
                }
                if ("Doctorsummarize".equals(tagName)) {
                    sb.append(data);
                }
            }*/

        }
    }
}
